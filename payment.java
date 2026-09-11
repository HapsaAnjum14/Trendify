import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class PaymentController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        PaymentRequest paymentRequest = gson.fromJson(reader, PaymentRequest.class);

        PaymentResponse paymentResponse = processPayment(paymentRequest);

        PrintWriter out = response.getWriter();
        out.print(gson.toJson(paymentResponse));
        out.flush();
    }

    private PaymentResponse processPayment(PaymentRequest request) {
        // Dummy logic for payment processing
        if (request.getCardNumber().equals("1234567890123456")) {
            return new PaymentResponse(true, "Transaction Approved");
        } else {
            return new PaymentResponse(false, "Invalid Card Number");
        }
    }
}

class PaymentRequest {
    private String cardNumber;
    private String cardHolder;
    private String expiryDate;
    private String cvv;

    // Getters and Setters
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    public String getCardHolder() { return cardHolder; }
    public void setCardHolder(String cardHolder) { this.cardHolder = cardHolder; }
    public String getExpiryDate() { return expiryDate; }
    public void setExpiryDate(String expiryDate) { this.expiryDate = expiryDate; }
    public String getCvv() { return cvv; }
    public void setCvv(String cvv) { this.cvv = cvv; }
}

class PaymentResponse {
    private boolean success;
    private String message;

    public PaymentResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    // Getters
    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
}