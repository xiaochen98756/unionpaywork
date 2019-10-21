package BigData;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * DateAndCalendar class
 *
 * @author wangxiaochen
 * @date 2019/10/21
 */
public class DateAndCalendar {

    public static void main(String[] args) throws ParseException {

        long now=System.currentTimeMillis();
        System.out.println("now: "+now);

        Date date=new Date(now);
        //System.out.println("date= "+date);

        Calendar calendar=Calendar.getInstance();
        //System.out.println("calendar= "+calendar.getTime().toString());

        //格式化
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println("before: "+date.toString());
        System.out.println("after: "+sdf.format(date));

        //String转换为Date类型
        String datestr="2015-06-01";
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
        Date date1=sdf1.parse(datestr);
        System.out.println(date1.toString());

        //Date类型转换为String类型
        String string=sdf1.format(date1);
        System.out.println(string);

        //设置生日 String
        String birthday="2019-10-21";

        //将string转为日期类型
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date2=simpleDateFormat.parse(birthday);

        //对日期进行计算
       Date spec= calculateBirthDay(date2);
        //将计算结果转换为String类型，输出
        String result=simpleDateFormat.format(spec);
        System.out.println(result.toString());
    }

    /**
     * 传入一个日期，计算出该日期两个星期前周六 的日期
     *
     */
    public static Date calculateBirthDay(Date birthdayDate){
        Calendar calendar=new GregorianCalendar();

        //接收传入的生日日期
        calendar.setTime(birthdayDate);

        //提前两周
        calendar.add(Calendar.WEEK_OF_MONTH,-2);

        //星期六
        calendar.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY);

        return calendar.getTime();


    }
}
