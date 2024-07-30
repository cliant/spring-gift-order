# spring-gift-order

 
 ## 상품 옵션 API
|URL|메서드|기능|설명|
|---------|----|-------|--------|
|/api/products/{productId}/options|GET|상품 옵션 목록 조회|특정 상품에 대한 모든 옵션을 조회한다.|
|/api/products/{productId}/options|POST|상품 옵션 추가|상품에 옵션을 추가한다.|
|/api/products/{productId}/options/{optionId}|PUT|상품 옵션 수정|기존 상품 옵션의 정보를 수정한다.|
|/api/products/{productId}/options/{optionId}|DELETE|상품 옵션 삭제|기존 제품 옵션을 삭제한다.|




1. 상품 옵션 조회


Request
```JSON
GET /api/products/1/options HTTP/1.1
```


Response
```JSON
  HTTP/1.1 200 
  Content-Type: application/json
  
  [
    {
      "id": 464946561,
      "name": "01. [Best] 시어버터 핸드 & 시어 스틱 립 밤",
      "quantity": 969
    }
  ]
```




2. 상품 옵션 추가


Request
```JSON
POST /api/products/1/options HTTP/1.1
Content-Type: application/json

{
      "name": "01. [Best] 시어버터 핸드 & 시어 스틱 립 밤",
      "quantity": 969
}
```


Response
```JSON
HTTP/1.1 201 Created
Content-Type: application/json

{
      "id": 464946561,
      "name": "01. [Best] 시어버터 핸드 & 시어 스틱 립 밤",
      "quantity": 969
}
```


#### Fail 
|Code|설명|
|----|--------|
|404 NOT FOUND|존재하지 않는 카테고리|
|409 CONFLICT|이미 존재하는 상품|




3. 상품 옵션 수정


Request
```JSON
PUT /api/products/1/options/1 HTTP/1.1
Content-Type: application/json

{
      "name": "02. [Best] 체리 블라썸 핸드 & 체리 블라썸 립 밤",
      "quantity": 953
}
```


Response
```JSON
HTTP/1.1 200
Content-Type: application/json

{
      "id": 464946231,
      "name": "02. [Best] 체리 블라썸 핸드 & 체리 블라썸 립 밤",
      "quantity": 953
}
```


#### Fail 
|Code|설명|
|----|--------|
|404 NOT FOUND|존재하지 않는 상품|




4. 상품 옵션 삭제


Request
```JSON
DELETE /api/products/1/options/1 HTTP/1.1
```


Response
```JSON
HTTP/1.1 204 NO CONTENT
```


#### Fail 
|Code|설명|
|----|--------|
|404 NOT FOUND|존재하지 않는 상품|




# 주문 API
|URL|메서드|기능|설명|
|---------|----|-------|--------|
|/api/orders|POST|주문하기	새 주문을 생성한다.|
|/api/orders?page=0&size=10&sort=orderDateTime,desc|GET|주문 목록 조회|(페이지네이션 적용)	주문 목록을 페이지 단위로 조회한다.|




1. 주문 생성


Request
```JSON
POST /api/orders HTTP/1.1
Authorization: Bearer {token}
Content-Type: application/json

{
    "optionId": 1,
    "quantity": 2,
    "message": "Please handle this order with care."
}
```


Response
```JSON
HTTP/1.1 201 Created
Content-Type: application/json

{
    "id": 1,
    "optionId": 1,
    "quantity": 2,
    "orderDateTime": "2024-07-21T10:00:00",
    "message": "Please handle this order with care."
}
```


#### Fail 
|Code|설명|
|----|--------|
|400 Bad Request|파라미터 오류|
|500 Internal Server Error|시스템 오류|




2. 주문 조회

   
Request
```JSON
GET /api/orders?page=0&size=10&sort=orderDateTime,desc HTTP/1.1
Authorization: Bearer {token}
Content-Type: application/json
```


Response
```JSON
  HTTP/1.1 200 
  Content-Type: application/json
  
  [
    {
      "id": 1,
      "optionId": 1,
      "quantity": 2,
      "orderDateTime": "2024-07-21T10:00:00",
      "message": "Please handle this order with care."
    }
  ]
```




