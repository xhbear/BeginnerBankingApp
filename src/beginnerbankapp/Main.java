package beginnerbankapp;

public class Main {

    public static void main(String[] args) {
        KrabsBank bank = KrabsBank.getInstance();
        bank.bankService();
    }
}