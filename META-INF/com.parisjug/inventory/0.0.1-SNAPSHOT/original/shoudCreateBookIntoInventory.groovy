package contracts

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'POST'
        url '/books'
        body("""
        {
            "name": "Kotlin",
            "price": "120"
        }
        """)
        headers {
            header('Content-Type', 'application/json')
        }
    }
    response {
        status 200
        body("""
        {
            "id": "dc8493d6-e2e3-47da-a806-d1e8ff7cd4df",
            "name": "Kotlin",
            "price": "120",
            "stock": 0
        }
        """)
        headers {
            header('Content-Type': 'application/json')
        }
    }
}