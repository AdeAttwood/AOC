pub fn part_1() {
    let input = include_str!(concat!(
        env!("CARGO_MANIFEST_DIR"),
        "\\src\\day_4.part_1.txt"
    ));

    let input_grid = input
        .trim()
        .split("\n")
        .map(|x| {
            x.chars()
                .map(|y| if y == '@' { 1 } else { 0 })
                .collect::<Vec<_>>()
        })
        .collect::<Vec<_>>();

    let mut count = 0;

    for x in 0..input_grid.len() {
        for y in 0..input_grid[x].len() {
            #[rustfmt::skip]
            let positions = vec![
                (-1, -1),(-1, 0),(-1, 1),
                (0,  -1),        ( 0, 1),
                (1,  -1),(1,  0),( 1, 1),
            ];

            if input_grid[x][y] == 0 {
                continue;
            }

            let c: i32 = positions
                .iter()
                .filter_map(|(dr, dc)| {
                    let nr = x as isize + dr;
                    let nc = y as isize + dc;

                    if nr >= 0
                        && nr < input_grid.len() as isize
                        && nc >= 0
                        && nc < input_grid[nr as usize].len() as isize
                    {
                        Some(input_grid[nr as usize][nc as usize])
                    } else {
                        None
                    }
                })
                .sum();

            if c < 4 {
                count += 1;
            }
        }
    }

    println!("Result Part 1: {:#?}", count);
}

fn do_grid(grid: &Vec<Vec<i32>>) -> (i32, Vec<Vec<i32>>) {
    let mut new_grid = grid.clone();
    let mut count = 0;

    for x in 0..grid.len() {
        for y in 0..grid[x].len() {
            #[rustfmt::skip]
            let positions = vec![
                (-1, -1),(-1, 0),(-1, 1),
                (0,  -1),        ( 0, 1),
                (1,  -1),(1,  0),( 1, 1),
            ];

            if grid[x][y] == 0 {
                continue;
            }

            let c: i32 = positions
                .iter()
                .filter_map(|(dr, dc)| {
                    let nr = x as isize + dr;
                    let nc = y as isize + dc;

                    if nr >= 0
                        && nr < grid.len() as isize
                        && nc >= 0
                        && nc < grid[nr as usize].len() as isize
                    {
                        Some(grid[nr as usize][nc as usize])
                    } else {
                        None
                    }
                })
                .sum();

            if c < 4 {
                new_grid[x][y] = 0;
                count += 1;
            }
        }
    }

    (count, new_grid)
}

pub fn part_2() {
    let input = include_str!(concat!(
        env!("CARGO_MANIFEST_DIR"),
        "\\src\\day_4.part_1.txt"
    ));

    let mut input_grid = input
        .trim()
        .split("\n")
        .map(|x| {
            x.chars()
                .map(|y| if y == '@' { 1 } else { 0 })
                .collect::<Vec<_>>()
        })
        .collect::<Vec<_>>();

    let mut total_count = 0;
    loop {
        let (count, grid) = do_grid(&input_grid);
        if count == 0 {
            break;
        }

        input_grid = grid;
        total_count += count
    }

    println!("Result Part 2: {}", total_count);
}
