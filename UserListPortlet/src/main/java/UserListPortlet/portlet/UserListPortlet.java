package UserListPortlet.portlet;

import com.liferay.adaptive.media.exception.AMRuntimeException.IOException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import UserListPortlet.constants.UserListPortletKeys;

/**
 * @author Manu
 */
@Component(
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=UserList",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + UserListPortletKeys.USERLIST,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class UserListPortlet extends MVCPortlet {
	
	 @Override
	    public void processAction(ActionRequest actionRequest, ActionResponse actionResponse)
	            throws PortletException, java.io.IOException {

	        String cmd = ParamUtil.getString(actionRequest, "cmd");

	        if (cmd.equals("searchUsers")) {
	            String searchName = ParamUtil.getString(actionRequest, "searchName", "");
	            String searchSurname = ParamUtil.getString(actionRequest, "searchSurname", "");
	            String searchEmail = ParamUtil.getString(actionRequest, "searchEmail", "");
	            int page = ParamUtil.getInteger(actionRequest, "page", 1);

	            // Puedes realizar alguna acción con los parámetros recibidos si es necesario

	            // Redirige a la vista para que se muestren los resultados actualizados
	            sendRedirect(actionRequest, actionResponse);
	        }

	        super.processAction(actionRequest, actionResponse);
	    }

	    @Override
	    public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
	            throws IOException, PortletException, java.io.IOException {

	        String searchName = ParamUtil.getString(renderRequest, "searchName", "");
	        String searchSurname = ParamUtil.getString(renderRequest, "searchSurname", "");
	        String searchEmail = ParamUtil.getString(renderRequest, "searchEmail", "");
	        int page = ParamUtil.getInteger(renderRequest, "page", 1);
	        int pageSize = 5;

	        List<User> users = UserService.getUsers(searchName, searchSurname, searchEmail, page, pageSize);

	        renderRequest.setAttribute("users", users);
	        renderRequest.setAttribute("page", page);
	        renderRequest.setAttribute("pageSize", pageSize);
	        renderRequest.setAttribute("searchName", searchName);
	        renderRequest.setAttribute("searchSurname", searchSurname);
	        renderRequest.setAttribute("searchEmail", searchEmail);

	        super.doView(renderRequest, renderResponse);
	    }
	
}