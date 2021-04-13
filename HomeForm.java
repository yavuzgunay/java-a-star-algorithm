import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static java.lang.Integer.*;

public class HomeForm extends JFrame implements ActionListener {
    private JPanel MainPanel;
    private JPanel headerPanel;
    private JPanel infoPanel;
    private JPanel contentPanel;
    private JComboBox nodeCB;
    private JButton searchButton;
    private JButton checkAlgorithmButton;
    private JButton checkButton;
    private JButton checkSubnodeButton;
    private JComboBox subnodeCB;
    private String[][] Node=new  String[15][2];//this is Node Name + Heurists
    //String[0][1]
    private int space=0;
    private String[][][] SubNode=new String[15][15][5];//={ { { "S","S","S" }, { "A", "B","C" } }, { { "32", "23" } } };




    public void createTreeNode(String NodeName,String HeuristicValue){
        int spaceValue=99;
        boolean isValided=true;
        try {
                            Node[space][0]=NodeName;
                            Node[space][1]=HeuristicValue;
                           // System.out.println("\nNode Name :"+Node[space][0]+ "\nHeuristic Value : "+Node[space][1]);
            nodeCB.addItem(new AnyObject(NodeName, HeuristicValue).value);

                        space++;


        }catch (Exception e){
            System.out.println("Tree ERROR : \n"+e);
        }



    }


    public void AddTreeNode(){
        //Tree Node Level 0
        createTreeNode("S","50");//S is node name heuristic cost is 50
        //yes
        //Tree Node Level 1
        createTreeNode("A","35");
        createTreeNode("B","23");
        createTreeNode("C","32");
        //Tree Node Level 2
        createTreeNode("D","12");
        createTreeNode("E","24");
        createTreeNode("F","65");
        createTreeNode("N","28");
        createTreeNode("M","35");
        //Tree Node Target Level 3
        createTreeNode("G1","0");
        createTreeNode("G2","0");
        createTreeNode("G3","0");
    }

    public void AddSubTreeNode(){
        //S Subnodes A,B,C
        createSubTreeNode("S","A","125");
        createSubTreeNode("S","B","136");
        createSubTreeNode("S","C","128");
        //A Subnodes D,E
        createSubTreeNode("A","D","146");
        createSubTreeNode("A","E","175");
        //B Subnodes F,N
        createSubTreeNode("B","F","116");
        createSubTreeNode("B","N","145");
        //C Subnodes M
        createSubTreeNode("C","M","185");
        ///D Subnodes G1
        createSubTreeNode("D","G1","145");
        ///E Subnodes G2
        createSubTreeNode("F","G2","122");
        ///M Subnodes G3
        createSubTreeNode("M","G3","112");

        //176+217+147
    }




    public void createSubTreeNode(String NodeName,String SubNodeName,String Cost){
     int selectedSpace=0;
     int selectedSubSpace=0;
        int stop=99;
        try {
            for(int i=0;i<15;i++){
                for(int h=0;h<12;h++){
                    if(Node[i][0]==NodeName){
                        selectedSpace=i;
                    }
                }
            }
            for(int i=0;i<stop;i++){
                if(SubNode[selectedSpace][i][0]==null){
                    selectedSubSpace=i;
                    i=stop-1;
                }

            }
            int TotalValue = Integer.parseInt(Node[selectedSpace][1])+Integer.parseInt(Cost);
            SubNode[selectedSpace][selectedSubSpace][0]=NodeName;
            SubNode[selectedSpace][selectedSubSpace][1]=SubNodeName;
            SubNode[selectedSpace][selectedSubSpace][2]=Cost;
            subnodeCB.addItem(new AnyObject(SubNodeName, Cost).value);
            SubNode[selectedSpace][selectedSubSpace][3]=String.valueOf(TotalValue);

            //System.out.println("Node Name : "+SubNode[selectedSpace][selectedSubSpace][0]+ "\nSubNode Name : "+SubNode[selectedSpace][selectedSubSpace][1]+ "\nNode Value : "+SubNode[selectedSpace][selectedSubSpace][2]+ "\nNode Total Value : "+SubNode[selectedSpace][selectedSubSpace][3]);

        }
        catch (Exception e){
            System.out.println("Sub Node ERROR : \n"+e);
        }

    }

    public void displaySelectedTreeNode(String NodeName){
        try {
            String Content;
            Content="";
            for(int i=0;i<15;i++){
                if(Node[i][0]==NodeName){
                    Content +="Node Name :"+Node[i][0]+
                            "\nHeuristic : "+Node[i][1];
                    if(SubNode[i][0][0]!=null){
                        Content +="\n[";
                        for(int h=0;h<3;h++){
                            if(SubNode[i][h][0]==Node[i][0]){
                                Content+=SubNode[i][h][1]+",";
                            }
                        }
                        Content+="]";
                    }
                }


            }
            JOptionPane.showMessageDialog(null,Content);
        }catch (Exception e){

            System.out.println("Display ERROR : "+e);
        }

    }

    public void displayAllTreeNode(){
        try {
            String Content;
            Content="";
            for(int i=0;i<15;i++){
                if(Node[i][0]!=null){
                    Content +="\nNode Name :"+Node[i][0]+
                            "\nHeuristic : "+Node[i][1];
                    if(SubNode[i][0][0]!=null){
                        Content +="\n[";
                        for(int h=0;h<3;h++){
                            if(SubNode[i][h][0]==Node[i][0]){
                                Content+=SubNode[i][h][1]+",";
                            }
                        }
                        Content+="]";
                    }
                }


            }
            JOptionPane.showMessageDialog(null,Content);
        }catch (Exception e){

            System.out.println("Display ERROR : "+e);
        }

    }

    public void displaySelectedSubTreeNode(String SubNodeName){
        try {
            String Content;
            Content="";
            for(int i=0;i<15;i++){
                for(int h=0;h<4;h++){
                    if(SubNode[i][h][1]==SubNodeName){
                        Content +="\nMain Node Name :"+SubNode[i][h][0]+
                                "\nSub Node Name : "+SubNode[i][h][1]+
                                "\nCost Value : "+SubNode[i][h][2]+
                                "\nTotal Cost (G+H) : "+SubNode[i][h][3];

                        }
                    }
            }
            JOptionPane.showMessageDialog(null,Content);
        }catch (Exception e){

            System.out.println("Display ERROR : "+e);
        }

    }

    public void displayASearchAlgorithm(){
        int minValue =211;
        int totalCost=0;
        String minNode = "";
        int currentValue=0;
        int selectedNode=0;
        String DisplayValue="Node         Queue\n";
        for(int i=0;i<15;i++){
            DisplayValue+="\n"+Node[selectedNode][0]+"                ";
            for(int h=0;h<4;h++){
                if(SubNode[selectedNode][h][0]!=null && Node[selectedNode][0]!=SubNode[selectedNode][h][1]){
             DisplayValue+=SubNode[selectedNode][h][1]+"("+SubNode[selectedNode][h][3]+"),";

                 currentValue=Integer.parseInt(SubNode[selectedNode][h][3]);
                 if(currentValue<minValue){
                     minValue=currentValue;
                     minNode=SubNode[selectedNode][h][1];
                 }

             }
                else{
                    selectedNode=i;
                    continue;
                }
            }

          totalCost+=minValue;
          minValue=211;
          for(int j=i;j<15;j++){
              if(Node[j][0]==minNode){
                  i=j;
                  selectedNode=i;
                  minNode=Node[selectedNode][0];
                  System.out.println(Node[selectedNode][0]);

              }


          }
            if(Node[i][0]=="G1" || Node[i][0]=="G2" || Node[i][0]=="G3"){
                i=15;
            }
        }
        DisplayValue+="\ntime Complexity : 3\nSpace Complexity : "+space+"\nTotal Cost : "+totalCost;
        JOptionPane.showMessageDialog(null,DisplayValue);

    }



    @Override
    public void actionPerformed(ActionEvent e) {
      if(e.getSource()==searchButton){
          if(isValidated()){
              displaySelectedTreeNode(nodeCB.getSelectedItem().toString());
          }
      }
      if(e.getSource()==checkButton){
              displayAllTreeNode();

      }
      if(e.getSource()==checkSubnodeButton){
          displaySelectedSubTreeNode(subnodeCB.getSelectedItem().toString());
      }
      if(e.getSource()==checkAlgorithmButton){
          displayASearchAlgorithm();
      }

    }


    public HomeForm() {

        //displayAllTreeNode();
        setTitle("A Star Search Algorithm - Yavuz GUNAY");
        setSize(800, 650);
        headerPanel.setSize(600,50);
        MainPanel.setSize(800,650);
        infoPanel.setSize(400,300);
        contentPanel.setSize(400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        setUndecorated(true);
        add(MainPanel);
        nodeCB.addItem(new AnyObject("Select Node", "0").value);
        AddTreeNode();
        AddSubTreeNode();
        searchButton.addActionListener(this);
        checkButton.addActionListener(this);
        checkAlgorithmButton.addActionListener(this);
        nodeCB.addActionListener(this);
        checkSubnodeButton.addActionListener(this);


    }

    public static void main(String[] args) {
        HomeForm homeForm = new HomeForm();
        homeForm.setVisible(true);


    }


    private class AnyObject {
        private String value;
        private String text;
        public  AnyObject(String value, String text) {
            this.value = value;
            this.text = text;
        }
    }

    public boolean isValidated() {
        boolean validated = true;
        if(nodeCB.getSelectedItem()=="Select Node"){
            validated=false;
            JOptionPane.showMessageDialog(null,"Please Select Any Node");
        }
        return validated;
    }
}

