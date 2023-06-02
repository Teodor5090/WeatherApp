# WeatherApp

1. Read/List: This operation will allow users to view the weather forecast for a specific location. Users can specify the location by sending a GET request to the RESTful API endpoint with the location as a parameter. The system will then fetch the weather forecast data from an external weather API and return it to the user.

2. Create: This operation will allow administrators to add new locations to the system. Administrators can provide information such as location name, latitude, and longitude. This operation will involve sending a POST request to the RESTful API endpoint with the required information, which will then be persisted in the PostgreSQL database using Spring Data JPA.

3. Read/List: This operation will allow users to view the list of available locations in the system. Users can use filters such as location name or coordinates to refine their search. This operation will involve sending a GET request to the RESTful API endpoint, which will then retrieve the relevant locations from the PostgreSQL database using Spring Data JPA and return them to the user.

4. Update: This operation will allow administrators to update location details in the system. Administrators can modify any location properties such as name, latitude, or longitude. This operation will involve sending a PUT request to the RESTful API endpoint with the updated information, which will then be persisted in the PostgreSQL database using Spring Data JPA.

5. Delete: This operation will allow administrators to remove locations from the system. This operation will involve sending a DELETE request to the RESTful API endpoint with the ID of the location to be removed, which will then be removed from the PostgreSQL database using Spring Data JPA.
