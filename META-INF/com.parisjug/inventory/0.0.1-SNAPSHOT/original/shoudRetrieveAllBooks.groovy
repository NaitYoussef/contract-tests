package contracts

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'GET'
        url '/v1/v1/books'
        headers {
            header('Content-Type', 'application/json')
        }
    }
    response {
        status 200
        body("""
        [{
            "id": "d4d37e73-77a0-4616-8bd2-5ed983d45d14",
            "name": "Java",
            "price": "100",
            "stock": 100
        },{
            "id": "8364948b-6221-4cd8-9fd9-db0d17d45ef8",
            "name": "Kotlin",
            "price": "120",
            "stock": 20
        }]
        """)
        headers {
            header('Content-Type': 'application/json')
        }
    }
}