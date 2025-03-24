import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class AuctionApp extends JFrame {
    CardLayout cardLayout = new CardLayout();
    JPanel mainPanel = new JPanel(cardLayout);

    public AuctionApp() {
        setTitle("Auction System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel menuPanel = new JPanel(new GridLayout(1, 4));
        JButton homeButton = new JButton("Home");
        JButton sellButton = new JButton("Sell a Property");
        JButton buyButton = new JButton("Buy a Property");
        JButton accountButton = new JButton("Account");

        menuPanel.add(homeButton);
        menuPanel.add(sellButton);
        menuPanel.add(buyButton);
        menuPanel.add(accountButton);

        add(menuPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        JPanel homePage = createHomePage();
        JPanel auctionPage = createAuctionPage();
        JPanel sellPropertyPage = createSellPropertyPage();
        JPanel buyPropertyPage = createBuyPropertyPage();

        mainPanel.add(homePage, "Home");
        mainPanel.add(auctionPage, "Auction");
        mainPanel.add(sellPropertyPage, "Sell");
        mainPanel.add(buyPropertyPage, "Buy");

        homeButton.addActionListener(e -> cardLayout.show(mainPanel, "Home"));
        sellButton.addActionListener(e -> cardLayout.show(mainPanel, "Sell"));
        buyButton.addActionListener(e -> cardLayout.show(mainPanel, "Buy"));

        setVisible(true);
    }

    private JPanel createSellPropertyPage() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("Sell Your Property", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JLabel nameLabel = new JLabel("Property Name:");
        JTextField nameField = new JTextField();

        JLabel locationLabel = new JLabel("Location:");
        JTextField locationField = new JTextField();

        JLabel priceLabel = new JLabel("Starting Bid Price ($):");
        JTextField priceField = new JTextField();

        JLabel descriptionLabel = new JLabel("Description:");
        JTextField descriptionField = new JTextField();

        JButton submitButton = new JButton("List Property");
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(locationLabel);
        formPanel.add(locationField);
        formPanel.add(priceLabel);
        formPanel.add(priceField);
        formPanel.add(descriptionLabel);
        formPanel.add(descriptionField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);

        submitButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String location = locationField.getText().trim();
            String price = priceField.getText().trim();
            String description = descriptionField.getText().trim();

            if (name.isEmpty() || location.isEmpty() || price.isEmpty() || description.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(panel, "Property Listed Successfully!\n\n"
                        + "Name: " + name + "\n"
                        + "Location: " + location + "\n"
                        + "Starting Price: $" + price + "\n"
                        + "Description: " + description, "Success", JOptionPane.INFORMATION_MESSAGE);
                cardLayout.show(mainPanel, "Home");
            }
        });

        panel.add(title, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createHomePage() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("DUMMY AUCTION", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        panel.add(title, BorderLayout.NORTH);

        JButton enterAuctionButton = new JButton("Enter");
        enterAuctionButton.setFont(new Font("Arial", Font.BOLD, 14));
        enterAuctionButton.setPreferredSize(new Dimension(100, 40));
        enterAuctionButton.addActionListener(e -> cardLayout.show(mainPanel, "Auction"));

        JPanel centerPanel = new JPanel();
        centerPanel.add(enterAuctionButton);

        panel.add(centerPanel, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createAuctionPage() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel propertyLabel = new JLabel("Property: Marriott", JLabel.LEFT);
        propertyLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JTextArea auctionStatus = new JTextArea(10, 40);
        auctionStatus.setEditable(false);
        auctionStatus.setText("Starting Bid Price: $1000\n");
        auctionStatus.setFont(new Font("Arial", Font.PLAIN, 20));

        JTextField bidInput = new JTextField(10);
        JButton bidButton = new JButton("Bid");

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> cardLayout.show(mainPanel, "Home"));

        JPanel bidPanel = new JPanel();
        bidPanel.add(new JLabel("Enter Bid:"));
        bidPanel.add(bidInput);
        bidPanel.add(bidButton);
        bidPanel.add(exitButton);

        panel.add(propertyLabel, BorderLayout.NORTH);
        panel.add(new JScrollPane(auctionStatus), BorderLayout.CENTER);
        panel.add(bidPanel, BorderLayout.SOUTH);

        Random random = new Random();
        final int[] currentBid = {1000};

        bidButton.addActionListener(e -> {
            try {
                int playerBid = Integer.parseInt(bidInput.getText().trim());
                if (playerBid > currentBid[0]) {
                    auctionStatus.append("\nPlayer Bid: $" + playerBid);
                    int botBid = playerBid + random.nextInt(500);
                    auctionStatus.append("\nBot Bid: $" + botBid);
                    currentBid[0] = botBid;

                    String response = JOptionPane.showInputDialog("Do you have any more bids? (Y/N)");
                    if (response != null && response.equalsIgnoreCase("N")) {
                        auctionStatus.append("\nBot wins the auction!");
                    }
                } else {
                    auctionStatus.append("\nBid too low! Must be greater than $" + currentBid[0]);
                }
                bidInput.setText("");
            } catch (Exception ex) {
                auctionStatus.append("\nInvalid bid! Enter a number.");
            }
        });

        return panel;
    }

    private JPanel createBuyPropertyPage() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel propertyLabel = new JLabel("Property: Hilton Hotel", JLabel.LEFT);
        propertyLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JTextArea auctionStatus = new JTextArea(10, 40);
        auctionStatus.setEditable(false);
        auctionStatus.setText("Starting Bid Price: $2000\n");
        auctionStatus.setFont(new Font("Arial", Font.PLAIN, 20));

        JTextField bidInput = new JTextField(10);
        JButton bidButton = new JButton("Bid");

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> cardLayout.show(mainPanel, "Home"));

        JPanel bidPanel = new JPanel();
        bidPanel.add(new JLabel("Enter Bid:"));
        bidPanel.add(bidInput);
        bidPanel.add(bidButton);
        bidPanel.add(exitButton);

        panel.add(propertyLabel, BorderLayout.NORTH);
        panel.add(new JScrollPane(auctionStatus), BorderLayout.CENTER);
        panel.add(bidPanel, BorderLayout.SOUTH);

        final int[] currentBid = {2000};

        bidButton.addActionListener(e -> {
            try {
                int playerBid = Integer.parseInt(bidInput.getText().trim());
                if (playerBid > currentBid[0]) {
                    auctionStatus.append("\nBid: $" + playerBid);
                    currentBid[0] = playerBid;
                } else {
                    auctionStatus.append("\nBid too low!");
                }
                bidInput.setText("");
            } catch (Exception ex) {
                auctionStatus.append("\nInvalid bid!");
            }
        });

        return panel;
    }

    public static void main(String[] args) {
        new AuctionApp();
    }
}
