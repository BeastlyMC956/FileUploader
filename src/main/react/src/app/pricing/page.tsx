type Plan = {
  name: string;
  price: number;
  color: string;
  details: string[];
}
function PlanList(plan: Plan) {
  const topBorder = 'w-1/2 h-3/4 flex flex-col items-center justify-evenly ' + plan.color + 
  ' border-t-4 rounded-t-lg transition duration-150 hover:scale-125'
  return (
    <div className={topBorder}>
      <div className="h-1/2 pt-24">
        <h1 className="text-3xl">{plan.name}</h1>
        <h1 className="text-2xl flex justify-center items-start">{plan.price}
          {plan.price !== 0 ? (<span className="text-lg h-full">99</span>) : ''}
          $
        </h1>
      </div>
      <div id="detail" className="h-1/4">
          <ul>
            {plan.details.map((detail, index) => (
              <li key={index}>{detail}</li>
            ))}
          </ul>
      </div>
      {plan.price !== 0 ? (
        <div id="purchase" className="h-1/4">
          <button type="button" className="p-4 pl-6 pr-6 border-b border-b-amber-600">Purchase</button>
        </div>
      ) : ''}
      
    </div>
  )
}

export default function Pricing() {
  const listWidth = 'w-1/4 flex justify-center items-center';
    return (
    <div className="w-full h-screen text-center">
      <h1 className="text-4xl">Pricing Options</h1>
        <div className="h-4/5">
          <ul className="flex h-full justify-evenly">
            <li className={listWidth}>
               {PlanList({ 
                name: 'Free Plan', 
                price: 0, 
                color: 'border-t-gray-600', 
                details: ['10 MB File Uploads', '200 MB Storage', 'Sometimes Avaliable']
                })}
            </li>
            <li className={listWidth}>
               {PlanList({ 
                name: 'Basic Plan', 
                price: 99, 
                color: 'border-t-green-600', 
                details: ['50 MB File Uploads', '1 GB Storage', 'Always Avaliable']
                })}
            </li>
            <li className={listWidth}>
              {PlanList({ 
              name: 'Premium', 
              price: 199, 
              color: 'border-t-amber-600', 
              details: ['50 MB File Uploads', '25 GB Storage', 'Always Avaliable']
              })}
            </li>
            <li className={listWidth}>
              {PlanList({ 
              name: 'Deluxe', 
              price: 299, 
              color: 'border-t-red-600', 
              details: ['50 MB File Uploads', '100 GB Storage', 'Always Avaliable']
              })}
            </li>
          </ul>
        </div>
    </div>
    )
}