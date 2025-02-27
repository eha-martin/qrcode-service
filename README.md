# QR Code Service

This project is a QR code generation service built with Spring Boot and ZXing. The development was carried out by following the requirements provided by [Hyperskill.org](https://hyperskill.org/), fulfilling each specification.

## Description

The service exposes an endpoint `/api/qrcode` that allows generating personalized QR codes based on the following parameters:

- **contents**: A text string to be encoded in the QR code (must not be empty or blank).
- **size**: The size of the QR code in pixels (between 150 and 350). Default value: 250.
- **correction**: The error correction level of the QR code (allowed values: L, M, Q, H). Default value: L.
- **type**: The type of image to generate (png, jpeg, or gif). Default value: png.

The service validates the parameters and, if they are correct, generates a real QR code using ZXing. If any parameter is invalid, it returns an error message in JSON format.

## Development Process

The project was completed by following the requirements provided by [Hyperskill.org](https://hyperskill.org/projects/385):

1. **Endpoint Design**: Created a REST controller that exposes `/api/qrcode` and validates the input parameters.
2. **ZXing Integration**: Added the ZXing dependency via Gradle and integrated it into the service to generate real QR codes.
3. **Service Configuration**: Implemented methods in `QrCodeService` to generate QR code images based on the content, size, and specified error correction level.
4. **Validation and Error Handling**: Set up parameter validations and developed a global exception handler to return clear error messages.
5. **Image Conversion**: Configured an HTTP converter (`BufferedImageHttpMessageConverter`) to serialize the images in the responses.

## Example Usage

### 1. Generate a QR Code with default parameters:

**HTTP GET Request:**

`GET /api/qrcode?contents=HelloWorld`

**Response:**

This will return a PNG image of a QR code with the content "HelloWorld", size 250x250, and error correction level "L".

### 2. Generate a QR Code with custom size and correction level:

**HTTP GET Request:**

`GET /api/qrcode?contents=HelloWorld&size=300&correction=M`

**Response:**

This will return a PNG image of a QR code with the content "HelloWorld", size 300x300, and error correction level "M".

### 3. Generate a QR Code with custom type (JPEG):

**HTTP GET Request:**

`GET /api/qrcode?contents=HelloWorld&type=jpeg`

**Response:**

This will return a JPEG image of a QR code with the content "HelloWorld", size 250x250, and error correction level "L".

### Error Handling Example

If the "contents" parameter is left empty, the response will be:

**HTTP GET Request:**

`GET /api/qrcode?contents=`

**Response:**

```json
{
    "error": "Contents cannot be null or blank"
}
```