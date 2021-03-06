/**
 * Broadcast receiver that set iptable rules on system startup.
 * This is necessary because the iptables rules are not persistent.
 * <p>
 * Copyright (C) 2009-2010  Rodrigo Zechin Rosauro
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @author Rodrigo Zechin Rosauro
 * @version 1.0
 */
package com.googlecode.droidwall;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Broadcast receiver that set iptables rules on system startup.
 * This is necessary because the rules are not persistent.
 * @author lcz
 */
public class BootBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            if (Api.isEnabled(context)) {
                if (!Api.applySavedIptablesRules(context, false)) {
                    // Error enabling firewall on boot
                    Toast.makeText(context, R.string.toast_error_enabling, Toast.LENGTH_SHORT).show();
                    Api.setEnabled(context, false);
                }
            }
        }
    }

}
