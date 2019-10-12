/**
 * Student class
 *
 * @author wangxiaochen
 * @date 2019/09/10
 */
public class Student {
    private String name;
    private int age;

    public Student() {
        // TODO Auto-generated constructor stub
    }

    public Student(String name,int age){
        this.name=name;
        this.age=age;
    }

    public void setName(String name){
        this.name=name;

    }

    public String getName(){
        return name;
    }

    public void setAge(int age){
        this.age=age;
    }

    public int getAge(){
        return age;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "student [name="+name+" , "+"age="+age+"]";
    }

}
