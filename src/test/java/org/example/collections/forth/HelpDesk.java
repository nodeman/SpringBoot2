package org.example.collections.forth;

import java.util.ArrayDeque;
import java.util.Queue;

import static org.example.collections.forth.Category.PRINTER;

public class HelpDesk {

    private final Queue<Enquiry> enquiries = new ArrayDeque<>();

    public static void main(String[] args) {
        HelpDesk helpDesk = new HelpDesk();
        helpDesk.enquire(Customer.JACK, Category.PHONE);
        helpDesk.enquire(Customer.JILL, PRINTER);

        //helpDesk.processAllEnquiries();

        helpDesk.processPrinterEnquiry();
        helpDesk.processGeneralEnquiry();
        helpDesk.processPrinterEnquiry();
    }

    public void enquire(final Customer customer, final Category category) {
        Enquiry enquiry = new Enquiry(customer, category);
        enquiries.offer(enquiry);

        System.out.println(enquiries);
    }

    public void processAllEnquiries() {
        /*
        final Enquiry enquiry = enquiries.remove();
        enquiry.getCustomer().reply("Have you tried turning it off and on again?");
        System.out.println(enquiries);*/

        while (!enquiries.isEmpty()) {
            final Enquiry enquiry = enquiries.remove();
            enquiry.getCustomer().reply("Have you tried turning it off and on again?");
        }
    }

    public void processPrinterEnquiry() {
        final Enquiry enquiry = enquiries.peek();
        System.out.println(enquiry);
        if (enquiry != null && enquiry.getCategory() == PRINTER) {
            enquiries.remove();
            enquiry.getCustomer().reply("Is it out of paper?");
        } else {
            System.out.println("No work to do, let's have some coffee");
        }
        System.out.println(enquiries);
    }

    public void processGeneralEnquiry() {
        final Enquiry enquiry = enquiries.peek();
        System.out.println(enquiry);
        if (enquiry != null && enquiry.getCategory() != PRINTER) {
            enquiries.remove();
            enquiry.getCustomer().reply("Have you tried turing it off and on again?");
        } else {
            System.out.println("No work to do, let's have some coffee");
        }

        System.out.println(enquiries);
    }
}
