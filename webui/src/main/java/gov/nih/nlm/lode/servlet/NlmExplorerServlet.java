package gov.nih.nlm.lode.servlet;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import uk.ac.ebi.fgpt.lode.exception.LodeException;
import uk.ac.ebi.fgpt.lode.model.ShortResourceDescription;
import uk.ac.ebi.fgpt.lode.servlet.ExplorerServlet;

/**
 * @author Daniel Davis
 * @date 2016/10/18
 * U.S. National Library of Medicine
 */
@Controller
@RequestMapping("/explore2")
public class NlmExplorerServlet extends ExplorerServlet {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    @RequestMapping(value = "/html", method = RequestMethod.GET)
    public ModelAndView describeResourceAsHtml(
            @RequestParam(value = "uri", required = true ) String uri,
            @RequestParam(value = "resource_prefix", required = false) String resource_prefix) throws LodeException {
        URI absuri = resolveUri(uri);
        if (absuri == null) {
            throw new LodeException("Malformed or empty URI request: " + uri);
        }

        ShortResourceDescription resourceDesc = getService().getShortResourceDescription(absuri,
                getConfiguration().getLabelRelations(), getConfiguration().getDescriptionRelations());
        if (resourceDesc == null) {
            log.info("No triples for subject: " + uri);
            throw new LodeNotFoundException("No triples for subject: " + uri);
        }

        ModelAndView mv = new ModelAndView();
        mv.setViewName("explore");
        if (resource_prefix == null) {
             resource_prefix = "";
        }
        mv.addObject("uri", absuri.toString());
        mv.addObject("resource_prefix", resource_prefix);
        mv.addObject("resource_desc", resourceDesc);
        return mv;
    }
}
