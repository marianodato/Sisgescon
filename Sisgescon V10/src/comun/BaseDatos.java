package comun;

/**
 *
 * @author Chiara
 */
public class BaseDatos {

    public String getAsunto(int tipo) {
        if (tipo == 1) {
            return "Paper recived";
        } else if (tipo == 2) {
            return "Recepcion de Articulo";
        } else if (tipo == 3) {
            return "Paper's modifications";
        } else if (tipo == 4) {
            return "Recepcion de articulo modificado";
        } else if (tipo == 5) {
            return "Paper's annulment";
        } else if (tipo == 6) {
            return "Eliminacion Articulo";
        } else if (tipo == 7) {
            return "Final copy";
        } else if (tipo == 8) {
            return "Aceptacion de articulo final";
        } else if (tipo == 9) {
            return "Remider of evaluations";
        } else if (tipo == 10) {
            return "Invitation for administrator";
        } else if (tipo == 11) {
            return "Invitation for reviewer";
        } else if (tipo == 12) {
            return "Conflicts and preferences";
        } else if (tipo == 13) {
            return "Assignment of papers";
        } else if (tipo == 14) {
            return "Changes in the assigment of papers";
        } else if (tipo == 15) {
            return "Confirmation and corrections";
        } else if (tipo == 16) {
            return "Confirmacion y correcciones";
        } else if (tipo == 17) {
            return "Notification and corrections";
        } else if (tipo == 18) {
            return "Notificación y correcciones";
        }
        return "";
    }

    public String getMensaje(int tipo) {
        if (tipo == 1) {
            return "Dear author,\n" + "\n" + "Your paper was successfully received.\nThe details of the congress are the following:\n";
        } else if (tipo == 2) {
            return "Estimado autor,\n\n" + "Se recibió satisfactoriamente su artículo.\nLos datos del congreso son los siguientes:\n";
        } else if (tipo == 3) {
            return "Dear author,\n" + "\n" + "The changes on your paper were successfully received.";
        } else if (tipo == 4) {
            return "Estimado autor,\n" + "\n" + "Se recibió satisfactoriamente las modificaciones de su artículo.";
        } else if (tipo == 5) {
            return "Dear author,\n We have received the annulment of your paper.";
        } else if (tipo == 6) {
            return "Estimado autor,\n" + "\n" + "Se recibió la cancelación de su artículo.";
        } else if (tipo == 7) {
            return "Dear author,\n" + "\n" + "The final copy of your paper was received.";
        } else if (tipo == 8) {
            return "Estimado autor,\n" + "\n" + "Se recibió satisfactoriamente la copia final de su artículo.";
        } else if (tipo == 9) {
            return "Dear reviewers,\n" + "\n" + "Please remember that the papers should be evaluated before the following date:";
        } else if (tipo == 10) {
            return "Dear colleague,\n" + "\n" + "You have been invited to participate as an administrator of the next congress:";
        } else if (tipo == 11) {
            return "Dear colleague,\n" + "\n" + "You have been invited to participate as an evaluator in the next congress:";
        } else if (tipo == 12) {
            return "Dear evaluator,\n" + "\n" + "Please confirm your conflicts and preferences.";
        } else if (tipo == 13) {
            return "Dear evaluator,\n" + "\n" + "It has been assigned to you the following papers.";
        } else if (tipo == 14) {
            return "Dear evaluator,\n" + "\n" + "There have been changes in the assignment of papers.";
        } else if (tipo == 15) {
            return "We inform you that your paper has been accepted for publication.\nHere are the corresponding corrections:\n";
        } else if (tipo == 16) {
            return "Estimado autor,\n" + "\n" + "Le informamos que su artículo ha sido aceptado para una posterior publicación.\nA continuación encontrará las evaluaciones correspondientes:";
        } else if (tipo == 17) {
            return "Dear author,\n" + "\n" + "We inform you that your paper has been rejected for publication.\nHere are the corresponding corrections:";
        } else if (tipo == 18) {
            return "Estimado autor,\nLe informamos que su articulo ha sido rechazado para una posterior publicación.\nA continuación encontrará las evaluaciones correspondientes:";
        }
        
        return "";
    }
}