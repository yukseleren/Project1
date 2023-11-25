import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

class Project1 {
    public static void main(String[] args) {
        FactoryImpl FactoryLine = new FactoryImpl();
        String Output = "";
        try {
            File myObj = new File(args[0]);
            System.out.println("1");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] x = data.split(" ");
                Product P;
                switch (x[0]) {
                    case "AF":
                        P = new Product(Integer.parseInt(x[1]), Integer.parseInt(x[2]));
                        FactoryLine.addFirst(P);
                        break;
                    case "AL":
                        P = new Product(Integer.parseInt(x[1]), Integer.parseInt(x[2]));
                        FactoryLine.addLast(P);
                        break;
                    case "A":
                        P = new Product(Integer.parseInt(x[2]), Integer.parseInt(x[3]));
                        try{
                        FactoryLine.add(Integer.parseInt(x[1]),P);}
                        catch (IndexOutOfBoundsException e){
                            Output = Output.concat("Index out of bounds.\n");
                        }

                        break;
                    case "RF":
                        try{
                            Product RF = FactoryLine.removeFirst();
                            Output = Output.concat(RF.toString() + "\n");
                        } catch(NoSuchElementException e) {
                            Output = Output.concat("Factory is empty.\n");
                        }
                        break;
                    case "RL":
                        try {
                            Product RL = FactoryLine.removeLast();
                            Output = Output.concat(RL.toString()+ "\n");
                        } catch(NoSuchElementException e) {
                            Output = Output.concat("Factory is empty.\n");
                    }
                        break;
                    case "RI":
                        try{
                            Product RI = FactoryLine.removeIndex(Integer.parseInt(x[1]));
                            Output = Output.concat(RI.toString()+ "\n");
                        } catch (IndexOutOfBoundsException e){
                            Output = Output.concat("Index out of bounds.\n");
                        }
                        break;
                    case "RP":
                        try{
                            Product S = FactoryLine.removeProduct(Integer.parseInt(x[1]));
                            Output = Output.concat(S.toString() + "\n");
                        }catch(NoSuchElementException e) {
                            Output = Output.concat("Product not found.\n");
                        }
                        break;
                    case "F":
                        try{
                            P = FactoryLine.find(Integer.parseInt(x[1]));
                            Output = Output.concat(P.toString()+"\n");
                        } catch (NoSuchElementException e){
                            Output = Output.concat("Product not found.\n");
                    }
                        break;
                    case "G":
                        try{
                            P = FactoryLine.get(Integer.parseInt(x[1]));
                            Output = Output.concat(P.toString()+"\n");
                        }catch(IndexOutOfBoundsException e){
                            Output = Output.concat("Index out of bounds.\n");
                        }

                        break;
                    case "U":
                        try{
                            P = FactoryLine.update(Integer.parseInt(x[1]),Integer.parseInt(x[2]));
                            Output = Output.concat(P.toString()+"\n");
                        } catch (NoSuchElementException e){
                            Output = Output.concat("Product not found.\n");
                        }
                        break;
                    case "FD":
                            Integer FD = FactoryLine.filterDuplicates();
                            Output = Output.concat(FD + "\n");
                        break;
                    case "R":
                        FactoryLine.reverse();
                        Output = Output.concat(FactoryLine.print());
                        // System.out.println(S);
                        break;
                    case "P":
                        Output = Output.concat(FactoryLine.print());
                        break;

                }
            }
            File myfile2 = new File(args[1]);
            FileWriter bst = new FileWriter(myfile2);
            bst.write(Output);
            bst.close();
            System.out.println(Output);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}