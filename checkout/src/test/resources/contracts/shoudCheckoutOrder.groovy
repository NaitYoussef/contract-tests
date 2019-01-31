package contracts

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'POST'
        url '/checkout'
        body("""
        {
            "bookId": "d4d37e73-77a0-4616-8bd2-5ed983d45d14",
            "number": 2,
            "clientId": "yannick"
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
            "bookId": "d4d37e73-77a0-4616-8bd2-5ed983d45d14",
            "number": 2,
            "clientId": "yannick"
        }
        """)
        headers {
            header('Content-Type': 'application/json')
        }
    }
}