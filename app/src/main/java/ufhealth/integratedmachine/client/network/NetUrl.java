package ufhealth.integratedmachine.client.network;

import rx.Observable;
import java.util.List;
import retrofit2.http.POST;
import ufhealth.integratedmachine.client.bean.BaseReturnData;
import ufhealth.integratedmachine.client.bean.zxzx.HotDepartment;

public interface NetUrl
{
    @POST("general/hospitalHotDepartment/findHospitalHotDepartment")
    Observable<BaseReturnData<List<HotDepartment>>> getHotDepartments();
}