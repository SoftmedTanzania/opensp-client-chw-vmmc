package org.smartregister.chw.vmmc.listener;


import android.view.View;

import org.smartregister.chw.vmmc.fragment.BaseVmmcCallDialogFragment;
import org.smartregister.chw.vmmc.util.VmmcUtil;
import org.smartregister.vmmc.R;

import timber.log.Timber;

public class BaseVmmcCallWidgetDialogListener implements View.OnClickListener {

    private BaseVmmcCallDialogFragment callDialogFragment;

    public BaseVmmcCallWidgetDialogListener(BaseVmmcCallDialogFragment dialogFragment) {
        callDialogFragment = dialogFragment;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.vmmc_call_close) {
            callDialogFragment.dismiss();
        } else if (i == R.id.vmmc_call_head_phone) {
            try {
                String phoneNumber = (String) v.getTag();
                VmmcUtil.launchDialer(callDialogFragment.getActivity(), callDialogFragment, phoneNumber);
                callDialogFragment.dismiss();
            } catch (Exception e) {
                Timber.e(e);
            }
        } else if (i == R.id.call_vmmc_client_phone) {
            try {
                String phoneNumber = (String) v.getTag();
                VmmcUtil.launchDialer(callDialogFragment.getActivity(), callDialogFragment, phoneNumber);
                callDialogFragment.dismiss();
            } catch (Exception e) {
                Timber.e(e);
            }
        }
    }
}
