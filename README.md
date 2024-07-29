**Required:**
JRE 17 or higher;
Gradle installed / supported by IDE 

**Optional:**
It's better to install the 'Lombok' plugin in your IDE to avoid error messages, but it will work without it.

**Steps to run:**
You can run all test by running command line command:   :test --tests "JUnitTestSuite" -Ptype=smoke -Denv=stage 


**Note:**
'type' parameter can be used by adding any other tags using comma separator: -Ptype=smoke,integration,sanity  
'env' parameter will set up baseUrl. Available values: stage, dev, prod. But only 'stage' works.

