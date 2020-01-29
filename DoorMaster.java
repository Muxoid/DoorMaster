
import java.util.*;
import java.io.*;

public class DoorMaster
{

static Scanner sc = new Scanner(System.in);
static List<float[][]> list = new LinkedList<float[][]>();
static String doortype = "";

public static String input(String question)
{
  System.out.print(question);

  String input = sc.nextLine();

  System.out.println("You entered " + input);

  return input;
}


public static String[] separate(String input)
{
       StringBuilder type = new StringBuilder();
       StringBuilder width = new StringBuilder();
       StringBuilder height = new StringBuilder();
       for (int i = 0; i < input.length(); i++) {
           char ch = input.charAt(i);
           if (Character.isAlphabetic(ch)) {
               type.append(ch);
           } else if (type.length() == 0){
               width.append(ch);
             } else {
               height.append(ch);
             }
           }


       String[] cabinet = new String[3];
       cabinet[0] = width.toString();
       cabinet[1] = type.toString();
       cabinet[2] = height.toString();
       return cabinet;

}

public static float[][] build(String[] cabinet)
{

  float width = 0;
  String type;
  float height = 0;

  try{
    width = Float.parseFloat(cabinet[0]);

  }catch(Exception e){
    System.out.println();
  }
  type = cabinet[1];

  try{
    height = Float.parseFloat(cabinet[2]);

  }catch(Exception e){
    System.out.println();
  }
  //height = Float.parseFloat(cabinet[2]);

  System.out.println("Building cabinet: " + width + type + height);

  float[][] fronts = new float[3][3];

  switch(type.toUpperCase())
  {
    case "W":
        fronts = wall(width, height);
        System.out.println(fronts[0][0] + " Doors at " + fronts[0][1] + ", " + fronts[0][2]);
      break;

    case "B":
        fronts = base(width);
        System.out.println(fronts[0][0] + " Drawers at " + fronts[0][1] + ", " + fronts[0][2]);
        System.out.println(fronts[1][0] + " Doors at " + fronts[1][1] + ", " + fronts[1][2]);
      break;
    case "BS":
        fronts = base(width);
        System.out.println(fronts[0][0] + " Drawers at " + fronts[0][1] + ", " + fronts[0][2]);
        System.out.println(fronts[1][0] + " Doors at " + fronts[1][1] + ", " + fronts[1][2]);
      break;
    case "SB":
        fronts = squareBase(width);
        System.out.println(fronts[0][0] + " Doors at " + fronts[0][1] + ", " + fronts[0][2]);
      break;

    case "SW":
        fronts = squareWall(width, height);
        System.out.println(fronts[0][0] + " Doors at " + fronts[0][1] + ", " + fronts[0][2]);
      break;
    case "T":
        fronts = tall(width);
        System.out.println(fronts[0][0] + " Doors at " + fronts[0][1] + ", " + fronts[0][2]);
      break;

    case "TS":
        fronts = tall(width);
        System.out.println(fronts[0][0] + " Doors at " + fronts[0][1] + ", " + fronts[0][2]);
      break;

    case "D":
        fronts = drawers(width, height);
      break;

    case "UB":
        fronts = utility(width, height);
        System.out.println(fronts[0][0] + " Doors at " + fronts[0][1] + ", " + fronts[0][2]);
        System.out.println(fronts[1][0] + " Doors at " + fronts[1][1] + ", " + fronts[1][2]);
        System.out.println(fronts[2][0] + " Doors at " + fronts[2][1] + ", " + fronts[2][2]);
      break;

    case "UBL":
        utilityLinene(width, height);
      break;

    case "PP":
        fronts = potsAndPans(width);
      break;

    case "SBD":
        fronts = squareBaseDrawer(width, height);
      break;

    case "CUSTOM":
        System.out.println("*************************WARNING***************************** ");
        System.out.println("All Custom inputs are exact no calculations will be performed.");
        fronts = custom(width, height);
      break;
    case "CUS":
        System.out.println("*************************WARNING***************************** ");
        System.out.println("All Custom inputs are exact no calculations will be performed.");
        fronts = custom(width, height);
      break;

    case "OFFSETALL":
        offsetall(height);
        listToString(list);
      break;

    case "DOORTYPE":
        doortype = input("Enter Door Type exactly: ");
      break;

    case "REMOVE":
      int index = (int) height;
      remove(index-1);
      break;

    case "POST":
      post();
      break;

    case "CLEAR":
      clear();
      break;

    case "HELP":
      help();
      break;
    default:
      System.out.println("This cabinet is not implemented yet. Email shawn.cormier@glenwoodkitchen.com");

  }
  return fronts;
}

public static float[][] wall(float x, float y)
{
  //fronts[0] = doors[]
  float[][] fronts = new float[1][3];

if (y <= 42.5)
{
  if(x < 24)
  {
    fronts[0][0] = 1f;
    fronts[0][1] = x-0.125f;
    fronts[0][2] = y-0.25f;

  }else if(x > 24)
  {
    fronts[0][0] = 2f;
    fronts[0][1] = (x/2)-0.125f;
    fronts[0][2] = y-0.25f;

  }
  if(x == 24)
  {
      String input = input("Cabinet = 24 in width one or 2 doors?");
      System.out.println("Enter 1 or 2");

      if( input.equals("1"))
      {
          fronts[0][0] = 1f;
          fronts[0][1] = x-0.125f;
          fronts[0][2] = y-0.25f;

      } else if ( input.equals("2"))
      {
          fronts[0][0] = 2f;
          fronts[0][1] = (x/2)-0.125f;
          fronts[0][2] = y-0.25f;

      }
    }
}else{
  if(x < 24)
  {
    fronts[0][0] = 2f;
    fronts[0][1] = x-0.125f;
    fronts[0][2] = y-30.25f-0.125f;

  }else if(x > 24)
  {
    fronts[0][0] = 2f;
    fronts[0][1] = (x/2)-0.125f;
    fronts[0][2] = y-30.25f-0.125f;

  }

  if(x == 24)
  {
      String input = input("Cabinet = 24 in width one or 2 doors?");
      System.out.println("Enter 1 or 2");

      if( input.equals("1"))
      {
          fronts[0][0] = 1f;
          fronts[0][1] = x-0.125f;
          fronts[0][2] = y-30.25f-0.125f;

      } else if ( input.equals("2"))
      {
          fronts[0][0] = 2f;
          fronts[0][1] = (x/2)-0.125f;
          fronts[0][2] = y-30.25f-0.125f;

      }
    }
}
  return fronts;
}

public static float[][] base(float x)
{

//fronts[0] == drawers[3]
//fronts[1] == doors[3]
  float fronts[][] = new float[2][3];

if (x < 24)
{
  fronts[0][0] = 1f;
  fronts[0][1] = x-0.125f;
  fronts[0][2] = 6f;


  fronts[1][0] = 1f;
  fronts[1][1] = x-0.125f;
  fronts[1][2] = 30.5f-6.375f;

}

if (x > 24)
{
  String inputdrawers = input("Cabinet > 24 in width one or 2 drawers? ");

  fronts[1][0] = 2f;
  fronts[1][1] = (x/2)-0.125f;
  fronts[1][2] = 30.5f-6.375f;


  if( inputdrawers.equals("1"))
  {
      fronts[0][0] = 1f;
      fronts[0][1] = x-0.125f;
      fronts[0][2] = 6f;

  } else if ( inputdrawers.equals("2"))
  {
      fronts[0][0] = 2f;
      fronts[0][1] = (x/2)-0.125f;
      fronts[0][2] = 6f;

  }
}
if(x == 24)
  {
      String inputdoors = input("Cabinet = 24 in width one or 2 doors?");
      String inputdrawers = input("Cabinet = 24 in width one or 2 drawers?");

      System.out.println("Enter 1 or 2");

      if( inputdoors.equals("1"))
      {
          fronts[1][0] = 1f;
          fronts[1][1] = x-0.125f;
          fronts[1][2] = 30.5f-6.375f;
      } else if ( inputdoors.equals("2"))
      {
          fronts[1][0] = 2f;
          fronts[1][1] = (x/2)-0.125f;
          fronts[1][2] = 30.5f-6.375f;
      }

      if( inputdrawers.equals("1"))
      {
          fronts[0][0] = 1f;
          fronts[0][1] = x-0.125f;
          fronts[0][2] = 6f;

      } else if ( inputdrawers.equals("2"))
      {
          fronts[0][0] = 2f;
          fronts[0][1] = (x/2)-0.125f;
          fronts[0][2] = 6f;
      }
    }
  return fronts;
}

public static float[][] squareBase(float x)
{
  float[][] fronts = new float[1][3];
  float y = 30.5f;

    fronts[0][0] = 2f;
    fronts[0][1] = x - 24.8125f;
    fronts[0][2] = y - 0.25f;


  return fronts;
}

public static float[][] squareWall(float x, float y)
{
  float[][] fronts = new float[1][3];

    fronts[0][0] = 2f;
    fronts[0][1] = x - 12.8125f;
    fronts[0][2] = y - 0.25f;


  return fronts;
}


public static float [][] tall(float x)
{
  float[][] fronts = new float[1][3];
    float y = 30.5f;

    if(x < 24)
    {
      fronts[0][0] = 1f;
      fronts[0][1] = x-0.125f;
      fronts[0][2] = y-0.25f;

    }else if(x > 24)
    {
      fronts[0][0] = 2f;
      fronts[0][1] = (x/2)-0.125f;
      fronts[0][2] = y-0.25f;

    }

    if(x == 24)
    {
        String input = input("Cabinet = 24 in width one or 2 doors?");
        System.out.println("Enter 1 or 2");

        if( input.equals("1"))
        {
            fronts[0][0] = 1f;
            fronts[0][1] = x-0.125f;
            fronts[0][2] = y-0.25f;

        } else if ( input.equals("2"))
        {
            fronts[0][0] = 2f;
            fronts[0][1] = (x/2)-0.125f;
            fronts[0][2] = y-0.25f;

        }
      }
      return fronts;
}

public static float[][] drawers(float x, float drawers)
{

  float[][] fronts;
if (drawers == 2)
  {
    fronts = new float[1][3];
    fronts[0][0] = drawers;
    fronts[0][1] = x-0.125f;
    fronts[0][2] = 15.0625f;

  }else
{
  fronts = new float[2][3];
  fronts[0][0] = 1f;
  fronts[0][1] = x-0.125f;
  fronts[0][2] =  6f;

  fronts[1][0] = drawers-1f;
  fronts[1][1] = x-0.125f;
  fronts[1][2] = (30.5f-(6f+((drawers+1f)*0.125f)))/(drawers-1f);


}
  return fronts;
}

public static float[][] potsAndPans(float x)
{
  float drawers = 3;
  float[][] fronts = new float[2][3];

  fronts[0][0] = 2f;
  fronts[0][1] = (x/2)-0.125f;
  fronts[0][2] =  6f;

  fronts[1][0] = drawers-1f;
  fronts[1][1] = x-0.125f;
  fronts[1][2] = (30.5f-(6f+((drawers+1f)*0.125f)))/(drawers-1f);

  return fronts;
}

public static float[][] utility(float x, float y)
{
      float[][] fronts = new float[3][3];

      if(x < 24)
      {
        fronts[0][0] = 1f;
        fronts[0][1] = x-0.125f;
        fronts[0][2] = 30.25f;

        fronts[1][0] = 1f;
        fronts[1][1] = x-0.125f;
        fronts[1][2] = 29.125f;

        fronts[2][0] = 1f;
        fronts[2][1] = x-0.125f;
        fronts[2][2] = y - 29.125f - 30.25f - 4.375f;

      }else if(x > 24)
      {

        fronts[0][0] = 2f;
        fronts[0][1] = (x/2)-0.125f;
        fronts[0][2] = 30.25f;

        fronts[1][0] = 2f;
        fronts[1][1] = (x/2)-0.125f;
        fronts[1][2] = 29.125f;

        fronts[2][0] = 2f;
        fronts[2][1] = (x/2)-0.125f;
        fronts[2][2] = y - 29.125f - 30.25f - 4.375f;

      }

      if(x == 24)
      {
          String input = input("Cabinet = 24 in width one or 2 doors?");
          System.out.println("Enter 1 or 2");

          if( input.equals("1"))
          {
            fronts[0][0] = 1f;
            fronts[0][1] = x-0.125f;
            fronts[0][2] = 30.25f;

            fronts[1][0] = 1f;
            fronts[1][1] = x-0.125f;
            fronts[1][2] = 29.125f;

            fronts[2][0] = 1f;
            fronts[2][1] = x-0.125f;
            fronts[2][2] = y - 29.125f - 30.25f - 4.375f;

          } else if ( input.equals("2"))
          {
            fronts[0][0] = 2f;
            fronts[0][1] = (x/2)-0.125f;
            fronts[0][2] = 30.25f;

            fronts[1][0] = 2f;
            fronts[1][1] = (x/2)-0.125f;
            fronts[1][2] = 29.125f;

            fronts[2][0] = 2f;
            fronts[2][1] = (x/2)-0.125f;
            fronts[2][2] = y - 29.125f - 30.25f - 4.375f;

          }
        }
        return fronts;
}

public static int utilityLinene(float x, float y)
{
  return 0;
}

public static float[][] squareBaseDrawer(float x, float drawers)
{

  float[][] fronts = new float[2][3];
  if (drawers == 2)
    {

      fronts[0][0] = drawers * 2;
      fronts[0][1] = x - 24.8125f;
      fronts[0][2] = 15.125f;

    }else
  {
    fronts[0][0] = 2f;
    fronts[0][1] = x - 24.8125f;
    fronts[0][2] = 6f;

    fronts[1][0] = (drawers-1f) * 2;
    fronts[1][1] = x - 24.8125f;
    fronts[1][2] = (30.5f-(6f+((drawers+1f)*0.125f)))/(drawers-1f);

  }
    return fronts;
}


public static float[][] custom(float x, float y)
{
  float[][] fronts = new float[1][3];
  fronts[0][0] = 1f;
  fronts[0][1] = x;
  fronts[0][2] = y;
  return fronts;
}

public static void offsetall(float distance)
{

  String input = "";

  while(!input.equals("X") && !input.equals("Y"))
  {
    input = input("Offset in X or Y ");
    input = input.toUpperCase();
  }


  for (int i = 0; i < list.size(); i++)
  {
      int j = 0;
      while ( list.get(i).length >1 && j < list.get(i).length )
        {
          if(input.equals("X"))
          {
            list.get(i)[j][1] = list.get(i)[j][1] - distance;
            j++;
          }
          if(input.equals("Y"))
          {
            list.get(i)[j][2] = list.get(i)[j][2] - distance;
            j++;
          }

        }
  }
}

public static void listToString(List<float[][]> list)
{
  float numFronts = 0;


  for (int i = 0; i < list.size(); i++)
  {
      int j = 1;
      System.out.println("#" + (i + 1) + Arrays.deepToString(list.get(i)));
      numFronts = numFronts + list.get(i)[0][0];
      while ( list.get(i).length >1 && j < list.get(i).length )
        {
            numFronts = numFronts + list.get(i)[j][0];
            j++;
        }
  }
  System.out.println("Total Number of fronts: " + numFronts);
}

public static void remove(int input)
{
  list.remove(input);
}

public static void post()
{
  String input = input("Please enter a filename: ");

  try (PrintWriter out = new PrintWriter(input + ".ahk");)
  {

      // Header

      out.println("F3::");
      out.println("Macro1:");
      out.println("WinActivate,  ahk_class tooltips_class32");
      out.println("Sleep, 333");
      out.println("WinActivate, 10.0.0.22 - Remote Desktop Connection ahk_class TscShellContainerClass");
      out.println("Sleep, 333");

      // repetitions
      int j;


      System.out.println(list.size());

      if(doortype.equals("X0-M30-00"))
      {
        for (int i = 0; i < list.size(); i++){

          j = 0;

            while ( list.get(i).length >1 && j < list.get(i).length )
              {
                out.println("Send, " + list.get(i)[j][0] );
                out.println("Sleep, 10");
                out.println("Send, {Tab}");
                out.println("Sleep, 10");
                out.println("Send, " + list.get(i)[j][1] );
                out.println("Sleep, 10");
                out.println("Send, {Tab}");
                out.println("Sleep, 10");
                out.println("Send, " + list.get(i)[j][2] );
                out.println("Sleep, 10");
                out.println("Click, 1147, 400 Left, , Down");
                out.println("Sleep, 10");
                out.println("Click, 1147, 400 Left, , Up");
                out.println("Sleep, 500");

              j++;
            }
          }
      }else if (doortype.equals("X1-M31-01"))
      {
        for (int i = 0; i < list.size(); i++){
          j = 0;

            while ( list.get(i).length >1 && j < list.get(i).length )
              {

                if(list.get(i)[j][2] < 8)
                {
                  out.println("Sleep, 10");
                  out.println("Click, 656, 375 Left, , Down");
                  out.println("Sleep, 10");
                  out.println("Click, 656, 375 Left, , Up");
                  out.println("Sleep, 10");
                  out.println("Send, {BackSpace}");
                  out.println("Sleep, 10");
                  out.println("Sleep, 500");
                  out.println("Send, " + doortype + "-DF");

                  out.println("Sleep, 500");
                  out.println("Click, 500, 434 Left, , Down");
                  out.println("Sleep, 10");
                  out.println("Click, 500, 434 Left, , Up");
                }else{
                  out.println("Sleep, 10");
                  out.println("Click, 656, 375 Left, , Down");
                  out.println("Sleep, 10");
                  out.println("Click, 656, 375 Left, , Up");
                  out.println("Sleep, 10");
                  out.println("Send, {BackSpace}");
                  out.println("Sleep, 10");
                  out.println("Sleep, 500");
                  out.println("Send, " + doortype);

                  out.println("Sleep, 500");
                  out.println("Click, 500, 434 Left, , Down");
                  out.println("Sleep, 10");
                  out.println("Click, 500, 434 Left, , Up");
                }


                out.println("Send, " + list.get(i)[j][0] );
                out.println("Sleep, 10");
                out.println("Send, {Tab}");
                out.println("Sleep, 10");
                out.println("Send, " + list.get(i)[j][1] );
                out.println("Sleep, 10");
                out.println("Send, {Tab}");
                out.println("Sleep, 10");
                out.println("Send, " + list.get(i)[j][2] );
                out.println("Sleep, 10");
                out.println("Click, 1147, 476 Left, , Down");
                out.println("Sleep, 10");
                out.println("Click, 1147, 476 Left, , Up");
                out.println("Sleep, 500");

              j++;
            }
          }
      }else{
        System.out.println("****************************************The door specified was not correct or is not supported.*******************************************");
        System.out.println("*******************************************************Using default**********************************************************************");
        for (int i = 0; i < list.size(); i++)
        {
          j = 0;
            while ( j < list.get(i).length )
              {
                out.println("Send, " + list.get(i)[j][0] );
                out.println("Sleep, 10");
                out.println("Send, {Tab}");
                out.println("Sleep, 10");
                out.println("Send, " + list.get(i)[j][1] );
                out.println("Sleep, 10");
                out.println("Send, {Tab}");
                out.println("Sleep, 10");
                out.println("Send, " + list.get(i)[j][2] );
                out.println("Sleep, 10");
                out.println("Click, 1147, 400 Left, , Down");
                out.println("Sleep, 10");
                out.println("Click, 1147, 400 Left, , Up");
                out.println("Sleep, 500");

              j++;
            }
          }
      }

      out.println("return");
  }catch(Exception e){
    System.out.println("Exception: " + e);
  }

}

public static void clear()
{
  list.clear();
  doortype = input("What is the new type of door? Enter it exactly: ");
  doortype = doortype.toUpperCase();
}

public static void help()
{
  System.out.println("This is the help menu all program options are listed as follow.");
  System.out.println();
  System.out.println("All stardard boxes should be listed in this program and should behave acordingly if not email shawn.cormier@glenwoodkitchen.com");
  System.out.println("All custom boxes should be entered manualy with the command CUS or CUSTOM and will take exact input.");

  System.out.println("Commands:");
  System.out.println("help - Shows this menu.");
  System.out.println("post - Will create a .ahk file and requires the AutoHotKey program to run.");
  System.out.println("remove(int) - Will remove a specific item in the list and requires an integer.");
  System.out.println("clear - Will clear the entire list.");
  System.out.println("offsetall - Will offset all of the fronts by the distance given.");
  System.out.println("q or quit - To quit the program.");
}


public static void main(String[] args)
  {
  //  List<float[][]> list = new LinkedList<float[][]>();

    doortype = input("What is the door type? Enter it exactly: ");
    doortype = doortype.toUpperCase();

    while(true)
    {
    String input =  input("Enter a cabinet: ");

    if (input.toUpperCase().equals("Q") || input.toUpperCase().equals("QUIT"))
    {
      System.out.println("bye");
      break;
    }

    String[] cabinet = new String[3];
    cabinet = separate(input);

    float[][] fronts = new float[4][3];
    fronts = build(cabinet);

    Iterator<float[][]> iterator = list.iterator();

    if (fronts[0][0] != 0)
    {
      list.add(fronts);
    }else{
      System.out.println("For the help menu enter help in the command line.");
    }
    listToString(list);


    }
  }
}
