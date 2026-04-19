# Gateway Module
# This module serves as the API gateway for routing requests.
# 使用 Spring Cloud Gateway 实现请求路由的功能

# Application properties for gateway

spring:
  cloud:
    gateway:
      routes:
        - id: service-a
          uri: lb://SERVICE-A
          predicates:
            - Path=/service-a/**

