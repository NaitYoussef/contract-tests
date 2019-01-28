package contracts

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'GET'
        url '/books/d4d37e73-77a0-4616-8bd2-5ed983d45d14'
        headers {
            header('Content-Type', 'application/json')
        }
    }
    response {
        status 200
        body("""
        {
            "id": "d4d37e73-77a0-4616-8bd2-5ed983d45d14",
            "name": "Java",
            "price": "100",
            "stock": 100
        }
        """)
        headers {
            header('Content-Type': 'application/json')
        }
    }
}