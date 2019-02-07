package contracts

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'POST'
        url '/v1/v1/books/d4d37e73-77a0-4616-8bd2-5ed983d45d14/reduce-stock/2'
        headers {
            header('Content-Type', 'application/json')
        }
    }
    response {
        status 200
        body("98")
        headers {
            header('Content-Type': 'application/json')
        }
    }
}