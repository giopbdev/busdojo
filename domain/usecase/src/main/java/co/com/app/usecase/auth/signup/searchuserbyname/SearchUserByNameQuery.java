package co.com.app.usecase.auth.signup.searchuserbyname;


import co.com.app.model.auth.signup.model.UserSignUp;
import co.com.app.model.auth.signup.value.UserName;
import co.com.app.model.shared.bus.query.QueryDataBus;
import co.com.app.model.shared.cqrs.Command;
import co.com.app.model.shared.cqrs.ContextData;
import co.com.app.model.shared.cqrs.Query;
import lombok.Data;

@Data
public class SearchUserByNameQuery implements QueryDataBus {

    private final Query<UserName, ContextData> query;


    public SearchUserByNameQuery(Query<UserName,ContextData> query) {
        this.query = query;
    }


    public static SearchUserByNameQuery fromCmd(Command<UserSignUp, ContextData> command){
        var queryMap = new Query<UserName, ContextData>(command.payload().name(), command.context());
        return new SearchUserByNameQuery(queryMap);
    }
}
