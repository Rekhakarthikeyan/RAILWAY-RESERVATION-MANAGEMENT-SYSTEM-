import java.util.*;
public class Main {
    public static void booktickets(Passenger p)
    {
        TicketBook tb = new TicketBook();
        if(TicketBook.lb==0 && TicketBook.mb==0 && TicketBook.ub==0 && TicketBook.rac==0 && TicketBook.wl==0) {
            System.out.println("No Tickets Available");
            System.out.println("-----------------------------------");
            return;
        }
        if((TicketBook.lb>0 && p.berthpreference.equals("L")) ||  (TicketBook.mb>0 && p.berthpreference.equals("M")) || (TicketBook.ub>0) && (p.berthpreference.equals("U")))
        {
            if (p.berthpreference.equals("L")) {
                tb.booktickets(p, "L", TicketBook.Alb.get(0));
                TicketBook.lb--;
                TicketBook.Alb.remove(0);
            } else if (p.berthpreference.equals("M")) {
                tb.booktickets(p, "M", TicketBook.Amb.get(0));
                TicketBook.mb--;
                TicketBook.Amb.remove(0);
            } else if (p.berthpreference.equals("U")) {
                tb.booktickets(p, "U", TicketBook.Aub.get(0));
                TicketBook.Aub.remove(0);
                TicketBook.ub--;
            }
        }
        else if (TicketBook.lb > 0) {
            System.out.println("Due to unavailability of requested berth you are alloted with lower berth. Sorry for the inconvenience.");
            tb.booktickets(p, "L", TicketBook.Alb.get(0));
            TicketBook.lb--;
            TicketBook.Alb.remove(0);
        } else if (TicketBook.mb > 0) {
            System.out.println("Due to unavailability of requested berth you are alloted with middle berth. Sorry for the inconvenience.");
            tb.booktickets(p, "M", TicketBook.Amb.get(0));
            TicketBook.mb--;
            TicketBook.Amb.remove(0);
        } else if (TicketBook.ub > 0) {
            System.out.println("Due to unavailability of requested berth you are alloted with upper berth. Sorry for the inconvenience.");
            tb.booktickets(p, "U", TicketBook.Aub.get(0));
            TicketBook.Aub.remove(0);
            TicketBook.ub--;
        }
        else if(TicketBook.rac>0)
        {
            tb.bookRACtickets(p,"RAC", TicketBook.Arac.get(0));
            TicketBook.Arac.remove(0);
            TicketBook.rac--;
            System.out.println("Due to the non availability of berths you alloted with RAC berth.");
        }

        else if(TicketBook.wl>0)
        {

            tb.bookWLtickets(p, "WL", TicketBook.Awl.get(0));
            TicketBook.Awl.remove(0);
            TicketBook.wl--;
            System.out.println("Due to the non availability of berths you are in waithing list.");
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        TicketBook tb = new TicketBook();
        boolean flag = true;
        while(flag)
        {
            System.out.print("1.  Book Ticket\n2. Cancel Ticket\n3. Show all the booked tickets\n4. Show available tickets\n5. Exit\n");
            int num = in.nextInt();
            switch(num)
            {
                case 1:
                    System.out.print("Enter the name of the passsenger: ");
                    String name = in.next();
                    System.out.print("Enter the age of the passsenger: ");
                    int age = in.nextInt();
                    System.out.print("Enter the gender of the passsenger (M/F): ");
                    String gender = in.next().toUpperCase();
                    System.out.print("Enter the berth preference of the passenger (L/M/U): ");
                    String berthpreference = in.next().toUpperCase();
                    Passenger p = new Passenger(name, age, gender, berthpreference);
                    booktickets(p);
                    break;

                case 2:
                    System.out.print("To cancel ticket enter the Passenger Id: ");
                    int c_passengerId = in.nextInt();
                    tb.canelticket(c_passengerId);
                    break;

                case 3:
                    tb.printAllthebookedTicket();
                    break;

                case 4:
                    tb.printAlltheavailableticket();
                    break;

                case 5:
                    flag=false;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + num);
            }
        }
    }
}
