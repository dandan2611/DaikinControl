# DaikinControl
Control your Daikin Air Conditioner with REST API.

## Default config

### Configure air conditioner IP address
Since this project is pretty old, please edit the IP of your air conditioner under the constant located in `fr.dandan2611.daikin.DaikinControl.service.DaikinServiceImpl` named `IP`.

You can also edit the code to use environment variables, application.properties, or even discover the air conditioner ip address at the startup of the program.

### Configure database access
Please edit the databases (MySQL) specs in application.properties.

## Available REST endpoints
You can see the endpoint parameters under the package `fr.dandan2611.daikin.DaikinControl.rest.controllers`

`GET /control/infos`

`POST /control/request`

`GET /sensor/infos`

`GET /database/search` Discontinued & not used

`POST /database/save` Debug ONLY

`POST /system/option/set`