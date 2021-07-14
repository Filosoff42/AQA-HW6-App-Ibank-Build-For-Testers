package page;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Value;

import static io.restassured.RestAssured.given;

public class DataHelper {

    private DataHelper() {}

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }
    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }
    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }
    @Value
    public static class VerificationCode {
        private String code;
    }
    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

//    @Value
//    public static class CardObject {
//        private String id;
//        private String number;
//        private int balance;
//    }
//
//    private static RequestSpecification requestSpec = new RequestSpecBuilder()
//            .setBaseUri("http://localhost")
//            .setPort(9999)
//            .setAccept(ContentType.JSON)
//            .setContentType(ContentType.JSON)
//            .log(LogDetail.ALL)
//            .build();
//
////    CardObject card1 = new CardObject("92df3f1c-a033-48e6-8390-206f6b1f56c0", "**** **** **** 0001", 10000);
////    CardObject card2 = new CardObject("0f3f5c2a-249e-4c3d-8287-09f7a039391d", "**** **** **** 0002", 10000);
//
//    public static void resetCardsBalance(CardObject card1, CardObject card2) {
//        // сам запрос
//        given() // "дано"
//                .spec(requestSpec) // указываем, какую спецификацию используем
//                .body(card1)// передаём в теле объект, который будет преобразован в JSON
//                .when() // "когда"
//                .post("/api/cards") // на какой путь, относительно BaseUri отправляем запрос
//                .then() // "тогда ожидаем"
//                .statusCode(200); // код 200 OK
//        given() // "дано"
//                .spec(requestSpec) // указываем, какую спецификацию используем
//                .body(card2)// передаём в теле объект, который будет преобразован в JSON
//                .when() // "когда"
//                .post("/api/cards") // на какой путь, относительно BaseUri отправляем запрос
//                .then() // "тогда ожидаем"
//                .statusCode(200); // код 200 OK
//    }

}
