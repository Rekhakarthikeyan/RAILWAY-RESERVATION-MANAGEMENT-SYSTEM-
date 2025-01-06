public class Passenger
{
    String name;
    int age;
    String gender;
    String berthpreference;
    static int num=0;
    int passengerId;
    String alottedberth;
    int seatno;

    public Passenger(String name, int age, String gender, String berthpreference)
    {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.berthpreference = berthpreference;
        this.passengerId = ++num;
        this.alottedberth = "";
        this.seatno = 0;
    }

}
