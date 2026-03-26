interface NotificationChannel {
    void send(String recipient, String message);
    String getChannelType();
}

class EmailService implements NotificationChannel {
    @Override
    public void send(String recipient, String message) {
        System.out.println("📧 Email sent to " + recipient + ": " + message);
    }
    
    @Override
    public String getChannelType() {
        return "EMAIL";
    }
}

class SMSService implements NotificationChannel {
    @Override
    public void send(String recipient, String message) {
        System.out.println("📱 SMS sent to " + recipient + ": " + message);
    }
    
    @Override
    public String getChannelType() {
        return "SMS";
    }
}

class SlackService implements NotificationChannel {
    @Override
    public void send(String recipient, String message) {
        System.out.println("💬 Slack message sent to " + recipient + ": " + message);
    }
    
    @Override
    public String getChannelType() {
        return "SLACK";
    }
}

class PushNotificationService implements NotificationChannel {
    @Override
    public void send(String recipient, String message) {
        System.out.println("🔔 Push notification sent to " + recipient + ": " + message);
    }
    
    @Override
    public String getChannelType() {
        return "PUSH";
    }
}

class NotificationService {
    private NotificationChannel notificationChannel;
    
    public NotificationService(NotificationChannel notificationChannel) {
        this.notificationChannel = notificationChannel;
    }
    
    public void notify(String recipient, String message) {
        notificationChannel.send(recipient, message);
    }
    
    public String getChannelType() {
        return notificationChannel.getChannelType();
    }
}

class MultiChannelNotificationService {
    private NotificationChannel[] channels;
    
    public MultiChannelNotificationService(NotificationChannel... channels) {
        this.channels = channels;
    }
    
    public void notifyAllChannels(String recipient, String message) {
        for (NotificationChannel channel : channels) {
            channel.send(recipient, message);
        }
    }
}

class UserAlert {
    private String userId;
    private String userEmail;
    private String userPhone;
    private NotificationService notificationService;
    
    public UserAlert(String userId, String userEmail, String userPhone) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
    }
    
    public void setNotificationChannel(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
    
    public void alertUser(String message) {
        if (notificationService != null) {
            String recipient = notificationService.getChannelType().equals("SMS") 
                ? userPhone : userEmail;
            notificationService.notify(recipient, message);
        }
    }
}

public class DIP_NotificationSystem {
    public static void main(String[] args) {
        System.out.println("===== Dependency Inversion Principle (DIP) =====\n");
        
        NotificationChannel emailChannel = new EmailService();
        NotificationChannel smsChannel = new SMSService();
        NotificationChannel slackChannel = new SlackService();
        NotificationChannel pushChannel = new PushNotificationService();
        
        System.out.println("--- Single Channel Notifications ---");
        
        NotificationService emailNotification = new NotificationService(emailChannel);
        emailNotification.notify("user@example.com", "Your order has been shipped!");
        
        NotificationService smsNotification = new NotificationService(smsChannel);
        smsNotification.notify("+1-555-0123", "Your verification code is 123456");
        
        NotificationService slackNotification = new NotificationService(slackChannel);
        slackNotification.notify("@john.doe", "Meeting reminder at 3 PM");
        
        NotificationService pushNotification = new NotificationService(pushChannel);
        pushNotification.notify("john.doe@device.id", "New message received!");
        
        System.out.println("\n--- Multi-Channel Notifications ---");
        MultiChannelNotificationService multiChannel = 
            new MultiChannelNotificationService(emailChannel, smsChannel, slackChannel);
        multiChannel.notifyAllChannels("john.doe", "Important system update!");
        
        System.out.println("\n--- Using with User Alerts ---");
        UserAlert alert = new UserAlert("U123", "user@example.com", "+1-555-9999");
        alert.setNotificationChannel(new NotificationService(emailChannel));
        alert.alertUser("Your password will expire in 7 days");
    }
}
