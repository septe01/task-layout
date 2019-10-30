package com.biceps_studio.task_layout

import org.junit.Test

import org.junit.Assert.*
import java.util.regex.Pattern
import java.util.regex.Pattern.compile

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    var email  = mails()
    var pass   = passwords()

//    email 5x test ( 2x true, 3x false)
    // test true
    @Test
    fun isMailTrue(){
    assertTrue(email.isEmailValid("septe@gmail.com"))
}
    @Test
    fun isMailTrueTwo(){
        assertTrue(email.mailValid("septesthia@gmail.com"))
    }

    //test false
    @Test
    fun isMailFalse(){
        assertFalse(email.isEmailValid("septesthia@gmail.com    "))//error jka pakai assertTrue karena ada space
    }
    @Test
    fun isMailFalseTwo(){
        assertFalse(email.mailValid("Septe@com  "))//error jka pakai assertTrue karena tidak valid
    }
    @Test
    fun isMailFalseTree(){
        assertFalse(email.mailses("com   "))//error jka pakai assertTrue karena tidak valid
    }

//    password 3x test ( 1x true, 2x false)
    //test Truete
    @Test
    fun isPasswordTrue(){
        assertTrue(pass.passTrue("septe123","septe123"))//true krna pass 1 dan 2 sama dan jumlah nya jg sama
    }

    //test False
    @Test
    fun isPasswordFalse(){
        assertFalse(pass.passTrue("septe123","septe")) //true karena pass 1 dan 2 tidak sama dan kurang dari 6
    }

    @Test
    fun isPasswordFalseTwo(){
        assertFalse(pass.passTrue("septe123","septe123 ")) //true karena pass 1 dan 2 sama tapi pass 2 ada space
    }
}

class mails{

    val emailRegex = compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"

    fun isEmailValid(email: String): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }

    fun mailValid(mail: String):Boolean{

       return emailRegex.matcher(mail).matches()
    }

    fun mailses(email: String): Boolean {
        return EMAIL_REGEX.toRegex().matches(email);
    }
}


class passwords{

    fun passTrue(pass1: String, pass2: String):Boolean {

        return (pass1 == pass2 && pass1.length >= 6)

    }
}
