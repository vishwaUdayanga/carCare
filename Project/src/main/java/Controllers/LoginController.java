package Controllers;

import Models.Admin;

import java.util.ArrayList;

public class LoginController {
    ArrayList<Admin> userDB;
    public LoginController()
    {
        userDB=new ArrayList<>();
        CreateDB();
    }
    private void CreateDB()
    {
        userDB.add(new Admin("vishwa","123","admin"));
        userDB.add(new Admin("janith","456","bike"));
        userDB.add(new Admin("yuki","789","rider"));
        userDB.add(new Admin("buddima","141","bank"));
    }

    public Admin validateUser(Admin currentUser)
    {
        for(Admin i:userDB)
        {
            if(i.getUserName().equals(currentUser.getUserName()) && i.getPassword().equals(currentUser.getPassword()))
            {
                return i;
            }
        }
        return null;
    }
}
