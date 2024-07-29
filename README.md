# spring-gift-order

#상품 옵션 API
|URL|메서드|기능|설명|
|---------|----|-------|--------|
|/api/products/{productId}/options|POST|상품 옵션 추가|상품에 옵션을 추가한다.|
/api/products/{productId}/options/{optionId}|PUT|상품 옵션 수정|기존 상품 옵션의 정보를 수정한다.|
/api/products/{productId}/options/{optionId}|DELETE|상품 옵션 삭제|기존 제품 옵션을 삭제한다.|
/api/products/{productId}/options|GET|상품 옵션 목록 조회|특정 상품에 대한 모든 옵션을 조회한다.|
