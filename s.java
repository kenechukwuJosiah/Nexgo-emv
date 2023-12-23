import com.nexgo.oaf.apiv3.APIProxy;
import com.nexgo.oaf.apiv3.DeviceEngine;
import com.nexgo.oaf.apiv3.SdkResult;
import com.nexgo.oaf.apiv3.device.reader.CardInfoEntity;
import com.nexgo.oaf.apiv3.device.reader.CardReader;
import com.nexgo.oaf.apiv3.device.reader.OnCardInfoListener;

public class CardReaderExample {

    public static void main(String[] args) {
        // Initialize the Nexgo SDK
        APIProxy apiProxy = DeviceEngine.getInstance().getProxy();
        apiProxy.init();

        // Get the card reader instance
        CardReader cardReader = apiProxy.getCardReader();

        // Set the card info listener
        cardReader.setOnCardInfoListener(new OnCardInfoListener() {
            @Override
            public void onCardInfo(CardInfoEntity cardInfoEntity) {
                // Handle the card information here
                String cardNumber = cardInfoEntity.getCardNo();
                String cardHolderName = cardInfoEntity.getCardHolderName();
                // ... other card information

                System.out.println("Card Number: " + cardNumber);
                System.out.println("Card Holder Name: " + cardHolderName);
            }

            @Override
            public void onError(int i, String s) {
                // Handle any errors that occur during card reading
                System.out.println("Error: " + s);
            }
        });

        // Start card reading
        SdkResult<CardReader> result = cardReader.startCardReader();

        if (result.isSuccess()) {
            System.out.println("Card reader started successfully");
        } else {
            System.out.println("Failed to start card reader: " + result.getError());
        }

        // Wait for card reading to complete
        try {
            Thread.sleep(5000); // Wait for 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Stop card reading
        cardReader.stopCardReader();

        // Release the Nexgo SDK resources
        apiProxy.release();
    }
}