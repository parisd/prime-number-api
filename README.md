# Project Summary

This project is a REST API that exposes a rest end point on port 8080 that expects a single integer argument. The call will calculate and return a list of prime numbers up to and including the supplied number. Minimal validation is performed on the supplied input.

The API can return the information as either XML or JSON, depending on the "Accept" header of the message.
* If no "Accept" header is supplied, JSON data will be returned.
* If "application/json" is supplied, JSON data will be returned.
* If "application/xml" is supplied, XML data will be returned.

# Building The Project
The project is build using maven. All tests will be run as part of the maven ```mvn clean install``` lifecycle (reports can be found in the target directory). This will generate an executable jar in the target directory and in your user's .m2.
The aforementioned jar can be run with the following command:
```
java -jar <pathToJar>
```

# Example Calls
Below is an example call for no Accept header, as well as "application/json" and "application/xml" Accept headers.

## No Accept Header
```
Request: http://localhost:8080/primes/16
Headers: "Accept: application/json"

Response:{"initial":16,"primes":[2,3,5,7,11,13]}
```

## JSON Accept Header
```
Request: http://localhost:8080/primes/16
Headers: "Accept: application/json"

Response:{"initial":16,"primes":[2,3,5,7,11,13]}
```

## XML Accept Header
```
Request: http://localhost:8080/primes/16
Headers: "Accept: application/xml"

Response: <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
          <primesResponse initial="16">
              <primes>2</primes>
              <primes>3</primes>
              <primes>5</primes>
              <primes>7</primes>
              <primes>11</primes>
              <primes>13</primes>
          </primesResponse>
```