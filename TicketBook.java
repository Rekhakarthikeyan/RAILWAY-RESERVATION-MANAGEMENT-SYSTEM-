import java.util.*;
public class TicketBook
{
    static int lb = 23;
    static int mb = 23;
    static int ub = 23;
    static int rac = 10;
    static int wl = 10;

    static List <Integer> Alb = new ArrayList<>(toInsertElementsInList(lb));
    static List <Integer> Amb = new ArrayList<>(toInsertElementsInList(mb));
    static List <Integer> Aub = new ArrayList<>(toInsertElementsInList(ub));
    static List<Integer> Arac = new ArrayList<>(toInsertElementsInList(rac));
    static List<Integer> Awl = new ArrayList<>(toInsertElementsInList(wl));

    static List<Integer> bookedpassengerIdlist = new ArrayList<>();
    static Queue<Integer> racq =   new LinkedList<>();
    static Queue<Integer> wlq =   new LinkedList<>();

    static Map<Integer, Passenger> bookedpassengerdatalist = new HashMap<>();

    static List<Integer> toInsertElementsInList(int num)
    {
        List<Integer> list=new ArrayList<>();
        for(int i=1;i<=num;i++){
            list.add(i);
        }
        return list;
    }

    public void booktickets(Passenger p, String alottedberth, int seatno)
    {
        p.alottedberth = alottedberth;
        p.seatno = seatno;
        bookedpassengerdatalist.put(p.passengerId, p);
        bookedpassengerIdlist.add(p.passengerId);
        System.out.println("Hurray! Your Ticket is successfully booked");
        System .out.println("Passenger Details");
        System.out.println("Passenger Id: " + p.passengerId);
        System.out.println("Passenger  Name: "+ p.name);
        System.out.println("Seat No: "+ seatno+alottedberth);
        System.out.println("--------------------------------------------------------------------");
    }

    public void bookRACtickets(Passenger p, String RACberth, int seatno)
    {
        p.alottedberth = RACberth;
        p.seatno = seatno;
        bookedpassengerdatalist.put(p.passengerId, p);
        racq.add(p.passengerId);
        System.out.println("Hurray! Your Ticket is sucessfully booked");
        System.out.println("Due to the non availability of berths you are in provided with RAC berth.");
        System .out.println("Passenger Details");
        System.out.println("Passenger Id: " + p.passengerId);
        System.out.println("Passenger  Name "+ p.name);
        System.out.println("Seatno "+ seatno+RACberth);
        System.out.println("--------------------------------------------------------------------");
    }

    public void bookWLtickets(Passenger p, String WLBerth, int seatno)
    {
        p.alottedberth = WLBerth;
        p.seatno = seatno;
        bookedpassengerdatalist.put(p.passengerId, p);
        wlq.add(p.passengerId);
        System.out.println("Due to the non availability of berths you are in the waiting list.");
        System .out.println("Passenger Details");
        System.out.println("Passenger Id: " + p.passengerId);
        System.out.println("Passenger  Name "+ p.name);
        System.out.println("Seatno "+ seatno+WLBerth);
        System.out.println("--------------------------------------------------------------------");
    }

    public void printAllthebookedTicket()
    {
        if(!(TicketBook.bookedpassengerdatalist).isEmpty()) //(TicketBook.bookedpassengerdatalist).size()>0
        {
            for (Map.Entry<Integer, Passenger> i : (TicketBook.bookedpassengerdatalist).entrySet())
            {
                Integer passengerId = i.getKey();
                Passenger passenger = i.getValue();
                System.out.println("Passenger ID: " + passengerId);
                System.out.println("Passenger Name: " + passenger.name);
                System.out.println("Passenger Age: " + passenger.age);
                System.out.println("Passenger Gender: " + passenger.gender);
                System.out.println("Passenger SeatNo: " + passenger.seatno + passenger.alottedberth);
                System.out.println("-------------------------------------------------------------");
            }
        }
            else
                System.out.println("No ticket has been booked");
    }

    public void printAlltheavailableticket()
    {
        if(Alb.size()==0 && Aub.size()==0 && Amb.size()==0)
            System.out.println("No ticket Available");
        else
        {
            System.out.println("Available Lower Berths: " + Alb.size());
            System.out.println("Available Middle Berths: " + Amb.size());
            System.out.println("Available Upper Berths: " + Aub.size());
            System.out.println("Available RAC Tickets: " + Arac.size());
            System.out.println("Available Waiting List Tickets: " + Awl.size());
        }
    }

    public void canelticket(int c_passengerId) {
        if (!bookedpassengerdatalist.containsKey(c_passengerId))
            System.out.println("Passenger ID Not Found");
        else
        {
            Passenger p = bookedpassengerdatalist.get(c_passengerId);
            bookedpassengerdatalist.remove(Integer.valueOf(c_passengerId));
            if (bookedpassengerIdlist.contains(p.passengerId))
            {
                bookedpassengerIdlist.remove(Integer.valueOf(c_passengerId));
                Integer seatno = p.seatno;
                String alottedberth = p.alottedberth;
                System.out.println("Ticket Cancelled Successfully");
                if (p.alottedberth.equals("L")) {
                    Alb.add(seatno);
                    lb++;
                } else if (p.alottedberth.equals("M")) {
                    Amb.add(seatno);
                    mb++;
                } else if (p.alottedberth.equals("U")) {
                    Aub.remove(seatno);
                    ub++;
                }
                if (racq.size() > 0) {
                    Integer racpassengerId = racq.poll();
                    Passenger p1 = bookedpassengerdatalist.get(racpassengerId);
                    Integer racseatno = p1.seatno;
                    Arac.add(racseatno);
                    rac++;

                    if (wlq.size() > 0) {
                        Integer wlpassengerId = wlq.poll();
                        Passenger p2 = bookedpassengerdatalist.get(wlpassengerId);
                        Integer wlseatno = p2.seatno;
                        Awl.add(wlseatno);
                        racq.add(wlpassengerId);
                        p2.seatno = Arac.getFirst();
                        p2.alottedberth = "RAC";
                        Arac.removeFirst();
                        rac--;
                        wl++;
                    }
                    Main.booktickets(p1);
                }

            }
            else if(racq.contains(Integer.valueOf(c_passengerId)))
            {
                racq.remove(Integer.valueOf(c_passengerId));
                System.out.println("Ticket Cancelled Successfully");
                Arac.add(p.seatno);
                rac++;
                if(!wlq.isEmpty())
                {
                    Integer wlpassengerId = wlq.poll();
                    Passenger p2 = bookedpassengerdatalist.get(wlpassengerId);
                    Integer wlseatno = p2.seatno;
                    Awl.add(wlseatno);
                    racq.add(wlpassengerId);
                    p2.seatno = Arac.getFirst();
                    p2.alottedberth = "RAC";
                    Arac.removeFirst();
                    rac--;
                    wl++;
                }

            }

        }
    }

}
