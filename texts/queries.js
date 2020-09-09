db.products.insert({
	"_id" : ObjectId("4c4b1476238d3b4dd5003981"),
	"slug": "wheelbarrow-9092", 
    "sku": "9092", 
    "name": "Extra Large Wheelbarrow", 
    "description": "Heavy duty wheelbarrow...", 
    "details": {
        "weight":47, 
        "weight_units": "lbs", 
        "model_num": 4039283402, 
        "manufacturer": "Acme", 
        "color": "Green"
    },
    "total_reviews": 4,
    "average_review": 4.5,
    "pricing": {
        "retail": 589700,
        "sale": 489700,
    },
    "price_history":[
        {
            "retail": 529700,
            "sale": 429700,
            "start": new Date(2010, 4,1),
            "end": new Date(2010, 4, 8)
        },
        {
            "retail": 529700,
            "sale": 529700,
            "start": new Date(2010, 4,9),
            "end": new Date(2010, 4, 16)
        },
     ],
     "primary_category": ObjectId("6a5b1476238d3b4dd5000048"),
     "category_ids": [
        ObjectId("6a5b1476238d3b4dd5000048"),
        ObjectId("6a5b1476238d3b4dd5000049")
     ],
     "main_cat_id": ObjectId("6a5b1476238d3b4dd5000048"),
     "tags": ["tools", "gardening", "soil"],
})


db.categories.insert({
    "_id": ObjectId("6a5b1476238d3b4dd5000048"),
    "slug": "gardening-tools",
    "name": "Gardening Tools",
    "description": "Gardening gadgets galore!",
    "parent_id": ObjectId("55804822812cb336b78728f9"),
        "ancestors": [
            {
                "name": "Home",
                "_id": ObjectId("558048f0812cb336b78728fa"),
                "slug": "home"
            },
            {
                "name": "Outdoors",
                "_id": ObjectId("55804822812cb336b78728f9"),
                "slug": "home"
            }
        ]
    }
)

db.reviews.insert({
	"_id": new ObjectId("4c4b1476238d3b4dd5000041"),
	"product_id": new ObjectId("4c4b1476238d3b4dd5003981"),
	"date": new Date(2010, 5, 7),
	"title": "Amazing",
	"text": "Has a squeaky wheel, but still a darn good wheel barrow.",
	"rating": 4,
	"user_id": new ObjectId("4c4b1476238d3b4dd5000041"),
	"username": "dgreenthumb",
	"helpful_votes": 3,
	"voter_ids": [ new ObjectId("4c4b1476238d3b4dd5000041"),new ObjectId("7a4f0376238d3b4dd5000003"),new ObjectId("92c21476238d3b4dd5000032")]
	}
)

/* Chapter 5 */




/* Chapter 6 */
db.reviews.count({'product_id': ObjectId("4c4b1476238d3b4dd5003981")})

db.reviews.aggregate([
    {$group : {_id:'$product_id',
    count:{$sum:1} }}
    ]);

/* 6.2.1 */
product = db.products.findOne({'slug':'wheelbarrow-9092'})
reviews_count = db.reviews.count({product_id: product['_id']})

product = db.products.findOne({'slug':'wheelbarrow-9092'})
ratingSummary = db.reviews.aggregate([{$match: {product_id: product['_id']}},{$group:{_id:'$product_id', count:{$sum:1}}}])

db.reviews.count({'product_id': product['_id']})

/* 
평균 리뷰 계산하기 
*/
product = db.products.findOne({'slug': 'wheelbarrow-9092'})

ratingSummary = db.reviews.aggregate([
    {$match : {'product_id': product['_id']}},
    {$group : {_id : '$product_id',
        average:{$avg:'$rating'},
        count: {$sum:1}}}
        ]).next();
		
/*
$group 앞에 $match를 두는 이유
이전 작업이 match일 경우 group에서 더 적은 도큐먼트를 가지고 작업을 할 수 있을 확률이 높다.
*/
		
/* 
등급별 리뷰 계산하기 
*/
countsByRating = db.reviews.aggregate([
    {$match : {'product_id': product['_id']}},{$group:{_id:'$rating', count:{$sum:1}}}
    ]).toArray();
	
/* id는 rating, field에는 count가 포함된 도큐먼트 배열 반환 */

/*
컬렉션 조인
*/
db.products.aggregate([{$group:{_id:'$main_cat_id', count:{$sum:1}}}]);
// 이 쿼리를 수행한 이후 결과는 _id, count 형태의 도큐먼트
// _id의 내용이 무엇인지 알 수 없음

db.mainCategorySummary.remove({});
db.products.aggregate([
 {$group : { _id:'$main_cat_id',
 count:{$sum:1}}}
]).forEach(function(doc){
 var category = db.categories.findOne({_id:doc._id});
 if (category !== null) {
 doc.category_name = category.name;
 }
 else {
 doc.category_name = 'not found';
 }
 db.mainCategorySummary.insert(doc);
});

// 집계 파이프라인은 커서 반환 가능. but, 각 도큐먼트에 대해 findOne을 수행하는 경우 오래 걸릴 수 있음

/* 
$out과 project 
*/
db.mainCategorySummary.remove({});
db.products.aggregate([
    {$group : {_id:'$main_cat_id',
        count:{$sum:1}}},
    {$out : 'mainCategorySummary'}
]);

// project -> 출력 필드를 제한(select col1, col2 ...)
db.products.aggregate([
    {$project : {category_ids:1}}
    ]);
	
/*
$unwind로 더 빨라진 조인
*/
db.products.aggregate([
    {$project : {category_ids:1}},
    {$unwind : '$category_ids'},
    {$group : {_id: '$category_ids', count:{$sum:1}}},
    {$out : 'countsByCategory'}
]);

// hive의 explode 처럼 sub-document를 풀어주기 위한 용도
db.categories.aggregate([
    {$project : {ancestors:1}},
    {$unwind : '$ancestors'}
])

////////////////////////////////////////////////////////////////////////////////////
db.listingsAndReviews.aggregate([
    {$project: {name:1,review_scores:1,last_review: 1}},
    {$match: {last_review: {$gte: new Date(2019,0,1)}}},
    {$group: {_id: '$review_scores.review_scores_rating', count:{$sum:1}}}]) 
	
	
db.listingsAndReviews.aggregate([
    {$project: {name:1,review_scores:1,last_review: 1}},
    {$limit: 100},
    {$match: {last_review: {$gte: new Date(2019,0,1)}}},
    {$group: {_id: '$review_scores.review_scores_rating', count:{$sum:1}}},
    {$sort: {'_id':-1}}
    //{$out: 'aggregateResult'}
]) 

pageNum = 12
db.listingsAndReviews.aggregate([
    {$match: {last_review: {$gte: new Date(2019,0,1)}}},
    {$project: {name:1, amenities:1}},
    {$skip : (pageNum-1)*12},
    {$unwind : '$amenities'}
])

/* addToSet과 push의 차이점을 확인하기 위한 쿼리들 */
db.listingsAndReviews.aggregate([
    {$project: {name:1,review_scores:1,last_review: 1, room_type:1, property_type:1}},
    {$limit: 100},
    {$match: {last_review: {$gte: new Date(2019,0,1)}}},
    {$group: {_id: '$review_scores.review_scores_rating', rooms:{$push:"$room_type"}, properties:{$addToSet:'$property_type'} ,review_count:{$sum:1}}},
    {$sort: {'_id':-1}}
    //{$out: 'aggregateResult'}
]);

db.listingsAndReviews.aggregate([
    {$project: {name:1,review_scores:1,last_review: 1, room_type:1, property_type:1}},
    {$limit: 100},
    {$match: {last_review: {$gte: new Date(2019,0,1)}}},
    {$group: {_id: '$review_scores.review_scores_rating', rooms:{$addToSet:"$room_type"}, properties:{$push:'$property_type'} ,review_count:{$sum:1}}},
    {$sort: {'_id':-1}}
    //{$out: 'aggregateResult'}
]);

////////////////////////////////////////////////////////////////////////////////////
/* 6.3 geonear 사용 예제 */
testSet = db.shipwrecks.findOne()
db.shipwrecks.aggregate([
    {
        $geoNear: { 
            spherical: true,        // 계산 방식 지정
            maxDistance:10000,      // 최대 거리 지정, GeoJSON 타입이기에 m 단위로 지정됨, maxDistance 이하 거리의 결과만 출력됨
        //  minDistance:100,        // 최소 거리 지정, GeoJSON 타입이기에 m 단위로 지정됨, minDistance 이상 거리의 결과만 출력됨
            near: {type: 'GeoJSON', coordinates: testSet.coordinates},  // 기준 좌표 설정, 여기서는 testSet의 좌표를 사용함
            distanceField: 'output',
            key: 'coordinates'
        }
    },
    {$limit: 50}
]);

testSet = db.shipwrecks.findOne()
db.shipwrecks.aggregate([
    {$geoNear: { spherical: true, maxDistance:10000, near: {type: 'GeoJSON', coordinates: testSet.coordinates},distanceField: 'output', key: 'coordinates'}},
    {$limit: 50},
    {$project:{_id:1, feature_type:1,coordinates:1}}
]);


////////////////////////////////////////////////////////////////////////////////////
/* 6.4 Document 재구성 */
db.restaurants.aggregate([
    {$project: {sumName : {$concat:['$borough', '-', '$cuisine', '-', '$name']}}},
    {$project: {sumName : {$toLower:'$sumName'}}}
])
