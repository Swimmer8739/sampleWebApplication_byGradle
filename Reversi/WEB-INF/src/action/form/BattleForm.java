package action.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import action.entity.PlayerColor;

public class BattleForm extends ActionForm {
    private List<PlayerColor>[][] board;
    private PlayerColor InitiativePlayer;

}
