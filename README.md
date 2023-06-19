# CurrencyConverter-TML

A rest web service that exposes an endpoint to convert a source currency amount, to a target currency.

The service reads the rates data from one of the providers (configurable at application properties level):
1. https://exchangeratesapi.io/
2. fixer.io
3. Currencylayer.com
4. https://api.forex/products


This project is designed to have three layers:
1. Api  - Responsiple to expose the endpoint and delegate request to core
2. Core - Where all application's use cases should be implemented
3. Data - Responsiple to fetch data from providers

This project follows Clean Architecture design, with the main idea to be that the Core module should be the core of the application,
where all the use cases are implemented. And everything else should be considered as an implementation detail.

Meaning that the core will not have dependencies on anything.
