/** 同報メール : サービス / applicatoin / BT0300 送信結果反映バッチ起動 */

package jp.ac.sundai.yobi.dohomail.service.application;

import jp.ac.sundai.yobi.dohomail.common.parameter.ServiceParameterContainer;
import jp.ac.sundai.yobi.dohomail.common.parameter.application.BT0300_ServiceParameter;
import jp.ac.sundai.yobi.dohomail.common.util.既存ライブラリ;

public class BT0300_Service {

    private BT0300_ServiceParameter getParameter(ServiceParameterContainer container) {

        return (BT0300_ServiceParameter) container.paramter;
    }

    /** 別プロセスで「BT0300 送信結果反映」を起動する。 */
    public void executeBatch(ServiceParameterContainer serviceParameterContainer) {

        BT0300_ServiceParameter parameter = getParameter(serviceParameterContainer);

        try {
            // 別プロセスで「BT0300 送信結果反映」を起動する。
            ProcessBuilder pb = new ProcessBuilder("BT0300 送信結果反映", "パラメータ");
            Process p = pb.start();
            if (p == null) {
                parameter.response.isSuccess = false;
                parameter.response.resultMessage = "送信結果反映プロセス「BT0300」の起動に失敗しました。";
                既存ライブラリ.writeDebugLog(parameter.response.resultMessage);
            }
            // 以下略
        } catch (Exception ex) {

            parameter.response.isSuccess = false;
            parameter.response.exception = ex;
            parameter.response.resultMessage = "送信結果反映プロセス「BT0300」の起動に失敗しました。";

            既存ライブラリ.writeDebugLog(parameter.response.exception);
            既存ライブラリ.writeDebugLog(parameter.response.resultMessage);
        }
    }
}
