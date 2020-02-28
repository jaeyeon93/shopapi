## 사용법


### Goods


> GET /goods/:goodId
> 
> Parameter goodId: int
>
> Response
```json
{
    "status": 201,
    "message": "상품 생성에 성공했습니다.",
    "response": {
        "id": 1,
        "name": "구매제거 테스트중",
        "provider": "StyleShare",
        "price": 20000,
        "options": [
            {
                "optionId": 1001,
                "color": "yellow",
                "size": "S",
                "stock": 10,
                "id": 1
            },
            {
                "optionId": 1002,
                "color": "yellow",
                "size": "M",
                "stock": 10,
                "id": 2
            },
            {
                "optionId": 1003,
                "color": "yellow",
                "size": "L",
                "stock": 10,
                "id": 3
            },
            {
                "optionId": 1004,
                "color": "blue",
                "size": "S",
                "stock": 10,
                "id": 4
            },
            {
                "optionId": 1005,
                "color": "blue",
                "size": "M",
                "stock": 10,
                "id": 5
            },
            {
                "optionId": 1006,
                "color": "blue",
                "size": "L",
                "stock": 10,
                "id": 6
            }
        ],
        "shipping": {
            "method": "FREE",
            "price": 0,
            "canBundle": true,
            "id": 1
        }
    }
}

```




