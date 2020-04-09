# SiriusXM_code challenge

## Problem Statement

### REST API Implementation

* Valid payload should be printed in the console from the method.
* Return corresponding http response code including vehicle id(something like UUID.randomUUID() would be enough) to the client as a part of location header

```
Path: /vehicle-api/v1/vehicles/vehicle
Content-Type: application/json
Request body: 
{
  "vin": "1A4AABBC5KD501999",
  "year": 2019,
  "make": "FCA",
  "model": "RAM"
  "transmissionType": "MANUAL",
}
```

### Error Handling

* Implement validation for "transmissionType" from the task 2 "Create Vehicle" , MUST accept only "MANUAL" or "AUTO" in the request body.
* Invalid payload use case:
  * Invalid payload MUST be thrown with a corresponding error code and error message in the response body
  * Any other error MUST throw 500 "Internal Server Error" http response code with some info message in the response body
  
### Logging

Logging Cross-cutting concern

We want to keep logging before and after method, however we don't want to put log statement in every method.
As a example below are given two classes "DBServiceA" and "HttpServiceB" and the corresponding methods getData(int id) and sendMessage(String message)

Implement Java logging - cross-cutting concern solution for those two classes.
Feel free  to use any framework and libraries (however Spring preferably). You can create/modify existing

Please explain briefly what are the pros and cons for your solution?

```

public class DBServiceA {
        
    public String getData(int id) {
        log.debug(id);

        /*
         assume that some data has been retrieved from DB by id
        */
        String resultData = "resultData";
        
        log.debug(resultData);
        return resultData;
    }
}

public class HttpServiceB {

    public String sendMessage(String message) {
        log.debug(message);
        
        /*
         assume that some message sends via rest client and gets "httpResponse"
        */
        String httpResponse = "httpResponse";
        
        log.debug(httpResponse);
        return httpResponse;

    }
}
```

---

## Problem Solution

* Lombok is used as an annotation-processor to avoid boiler plate codes.

---

* Asynchronous REST API is implemented through `@EnableAsync` annotation-based configuration in 
java class `DemoApplication` along with `VehicleService` as a dummy service.
* Valid payload is printed in the console using logger.
* Corresponding response code along with random UUID is returned as a part of location header. (UUID is included in location header, not in response body)

---

* Transmission type accepts only `MANUAL` or `AUTO`. It is implemented through `javax` constraint validator.
Custom annotation `@TransmissionType` is used for validating `transmissionType` in `Vehicle` payload.
* Invalid payload is validated as per `javax` validation and corresponding error code and message is thrown in response body.
* Any other error is throw with error code `500` through controller.

---

* `slf4j` is used for logging.
* With reference to the problem statement, logging is implemented in both `Controller` and `Service` layers.
* `DBServiceA` is referenced as `VehicleService` whereas `HttpServiceB` is referenced as `VehicleController` with 
logging kept before and after method.
