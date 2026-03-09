package ioc_md02.presentation;

public class InvoiceDetailView {
    private static InvoiceDetailView instance;

    private InvoiceDetailView() {}

    public static InvoiceDetailView getInstance() {
        if (instance == null) {
            instance = new InvoiceDetailView();
        }
        return instance;
    }
}
