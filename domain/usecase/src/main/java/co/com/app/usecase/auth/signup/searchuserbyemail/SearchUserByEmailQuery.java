package co.com.app.usecase.auth.signup.searchuserbyemail;


import co.com.app.model.auth.signup.model.UserSignUp;
import co.com.app.model.auth.signup.value.UserEmail;
import co.com.app.model.shared.bus.query.QueryDataBus;
import co.com.app.model.shared.cqrs.Command;
import co.com.app.model.shared.cqrs.ContextData;
import co.com.app.model.shared.cqrs.Query;
import lombok.Data;

@Data
public class SearchUserByEmailQuery implements QueryDataBus {

    private final Query<UserEmail, ContextData> query;


    public SearchUserByEmailQuery(Query<UserEmail,ContextData> query) {
        this.query = query;
    }


    public static SearchUserByEmailQuery fromCmd(Command<UserSignUp, ContextData> command){
        var queryMap = new Query<UserEmail, ContextData>(command.payload().email(), command.context());
        return new SearchUserByEmailQuery(queryMap);
    }


}
