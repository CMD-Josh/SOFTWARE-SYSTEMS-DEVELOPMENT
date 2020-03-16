<%-- 
    Document   : content
    Created on : Jan 4, 2020, 11:19:47 AM
    Author     : cgallen
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="org.solent.com504.project.model.auction.dto.Auction"%>
<c:set var = "selectedPage" value = "home" scope="request" />

<!-- start of home.jsp selectedPage=${selectedPage}-->
<jsp:include page="header.jsp" />

<!-- Begin page content -->
<main role="main" class="container">

    <H1>Home</H1>
    <table class="auctionsTable">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Start time</th>
                    <th scope="col">Type</th>
                    <th scope="col">Lots</th>
                    <th scope="col">Go to</th>
                    
                </tr>
            </thead>
            <tbody>
                <%--<c:forEach var="auction" items="${auctions}">--%>
                    <tr>
                        <td>${auction.id}</td>
                        <td>${auction.startTime}</td>
                        <td>${auction.auctionType}</td>
                        <td>${auction.lots}</td>
                        <td><button class="btn" type="submit" >Go</button></td>
                    </tr>
                <%--</c:forEach>--%>

            </tbody>
        </table>
</main>

<jsp:include page="footer.jsp" />
