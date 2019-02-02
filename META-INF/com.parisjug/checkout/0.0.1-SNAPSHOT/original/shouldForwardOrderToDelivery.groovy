package contracts

org.springframework.cloud.contract.spec.Contract.make {
    label '(groovy)should send order'
    input {
        triggeredBy('sendOrder()')
    }
    outputMessage {
        sentTo('orders')
        body("""
        {
            "bookId": "d4d37e73-77a0-4616-8bd2-5ed983d45d14",
            "number": 2,
            "clientId": "yannick"
        }
        """)
        headers {
            header('SENDER', 'checkout')
            messagingContentType(applicationJson())
        }
    }
}