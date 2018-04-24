package ufhealth.integratedmachine.client.ui.zxzx.presenter;

import ufhealth.integratedmachine.client.ui.base.BaseMvp_Presenter;
import ufhealth.integratedmachine.client.ui.zxzx.view_v.SpzxSelecDocAct_V;

public class SpzxSelecDocPresenter extends BaseMvp_Presenter
{

    private SpzxSelecDocAct_V spzxSelecDocActV;

    public SpzxSelecDocPresenter(SpzxSelecDocAct_V spzxSelecDocActV)
    {
        this.spzxSelecDocActV = spzxSelecDocActV;
    }

    public void refreshData()
    {

    }
}