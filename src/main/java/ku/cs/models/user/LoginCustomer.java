package ku.cs.models.user;

public class LoginCustomer {
    public static Customer customer = null;

    public LoginCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomerToNull(Customer customer) {
        this.customer = null;
    }
}
