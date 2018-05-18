package Main;

import GUIs.MenuPrincipal;
import java.awt.Dimension;

/**
 * @author Radames J Halmeman - rjhalmeman@gmail.com
 */
public class Main {

    public static String masterPath = System.getProperty("user.dir") + System.getProperty("file.separator") + "extratosCsv";

    public static void main(String[] args) {
           new MenuPrincipal(new Dimension(1300,800));
       // new LancamentoGUI_JTable();
        //coloque seu código aqui
//        DAOLancamento daoLancamento = new DAOLancamento();
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//
//        try {
//            Date dtIn = sdf.parse("12/02/2018");
//            Date dtFim = sdf.parse("15/02/2018");
//            List<Lancamento> ll = daoLancamento.listByContaIntervalo(1, dtIn, dtFim);
//            for (Lancamento lancamento : ll) {
//                System.out.println(sdf.format(lancamento.getDataLancamento()) + " - " + lancamento.getIdLancamento());
//            }
//        } catch (ParseException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }

}
