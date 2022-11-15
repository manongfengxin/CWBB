package cwbb.handler;

import cwbb.POJO.Dto.WxUserDto;

public class UserThreadLocal {


    private static final ThreadLocal<WxUserDto> LOCAL = new ThreadLocal<>();

    public static void put(WxUserDto userDto){
        LOCAL.set(userDto);
    }

    public static WxUserDto get(){
        return LOCAL.get();
    }

    public static void remove(){
        LOCAL.remove();
    }
}
