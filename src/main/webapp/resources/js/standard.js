/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function hideOtherExpendedRows() {
    var i = $('.ui-row-toggler.ui-icon-circle-triangle-s').length;
    if (i == 1) {
        return;
    }
    $('.ui-row-toggler.ui-icon-circle-triangle-s').trigger('click');
}