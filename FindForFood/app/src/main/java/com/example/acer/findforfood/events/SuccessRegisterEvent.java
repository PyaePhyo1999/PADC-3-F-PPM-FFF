package com.example.acer.findforfood.events;

import com.example.acer.findforfood.data.vo.RegisterVO;

/**
 * Created by Acer on 1/27/2018.
 */

public class SuccessRegisterEvent {
    private RegisterVO register;
    public RegisterVO getRegister(){
        return register;
    }
    public SuccessRegisterEvent(RegisterVO register){
        this.register = register;
    }

}
